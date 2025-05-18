# Authetication Mechanism

## Context and Problem Statement

To show user related content a user has to autheticate himself. A mechanism is needed to authenticate a user

## Considered Options

* Form Login
* Basic Auth
* JWT / OIDC

## Decision Outcome

Chosen option: "Form Login", because it is a standalone application which does not need the same auth mechanism on mutliple applications. Also for jwt a identity provider needed to be configured.
