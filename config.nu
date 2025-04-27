#!/usr/bin/env nu

def get_names [] {
    bash -c """
nix repl <<EOF
:lf .
builtins.toJSON outputs.nixosConfigurations.nova.config
EOF
    """
}

print (get_names)
