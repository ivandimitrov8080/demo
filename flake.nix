{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
  };
  outputs =
    { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = import nixpkgs { inherit system; };
    in
    {
      packages.${system} = {
        hello = pkgs.hello;
        default = self.packages.x86_64-linux.hello;
      };
      devShells.${system} = {
        default = pkgs.mkShell {
          buildInputs = with pkgs; [ go ];
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
