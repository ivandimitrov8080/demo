{
  inputs = {
    # nixpkgs
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    # preconfigured defaults
    configuration.url = "github:ivandimitrov8080/configuration.nix";
    # nvim config helper - needed to work with configuration ^
    nixvim-flake.url = "github:nix-community/nixvim";
    nixvim-flake.inputs.nixpkgs.follows = "nixpkgs";
    # neovim latest version
    neovim-nightly-overlay.url = "github:nix-community/neovim-nightly-overlay";
    neovim-nightly-overlay.inputs.nixpkgs.follows = "nixpkgs";
  };
  outputs =
    {
      self,
      nixpkgs,
      configuration,
      nixvim-flake,
      neovim-nightly-overlay,
    }:
    let
      system = "x86_64-linux";
      inherit (nixvim-flake.legacyPackages.${system}) makeNixvim;
      pkgs = import nixpkgs {
        inherit system;
        overlays = [
          (final: prev: {
            nixvim = makeNixvim {
              package = neovim-nightly-overlay.packages.${system}.default;
            };
          })
          configuration.overlays.default
        ];
      };
    in
    {
      packages.${system} = {
        hello = pkgs.hello;
        default = self.packages.x86_64-linux.hello;
      };
      devShells.${system} = {
        default = pkgs.mkShell {
          buildInputs = with pkgs; [
            go
            (nixvim.main.extend {
              lsp.servers.gopls.enable = true;
              plugins.dap-go.enable = true;
            })
          ];
        };
      };
      checks.${system} = {
        default = pkgs.testers.runNixOSTest {
          name = "test";
          nodes = {
            machine =
              { pkgs, ... }:
              {
                environment.systemPackages = [ pkgs.hello ];
              };
          };
          testScript =
            #py
            ''
              machine.wait_for_unit("multi-user.target");
              machine.succeed("hello");
            '';
        };
      };
    };
}
