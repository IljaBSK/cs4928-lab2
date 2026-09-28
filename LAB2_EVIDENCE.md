# CS4928 Lab 2 Evidence

## Baseline

Pinned SHA:
cf437186b2ae2d9100a187e0df3cce3a163e9b4b

Upstream test suite:
PASS

CS4928Lab2CheckTest:
PASS

Baseline tag:
CS4928_BASELINE


## Problem A

validateName mixes general validation with package-type-specific
normalisation.

Goal: move the varying name-normalisation algorithm behind a stable
policy while preserving every existing result.


## Refactoring A

Changes:
- Created PackageNamePolicy interface.
- Created PackageNamePolicies resolver.
- Moved package-name normalisation rules out of validateName.
- validateName now delegates normalisation to the correct policy.

Tests after Refactoring A:
- mvn test: PASS
- CS4928Lab2CheckTest: PASS
- git diff --check: PASS

Refactoring A commit SHA:
e082546

Before -> After:
Before, validateName contained the different package-name normalisation
rules. After, those rules are handled by separate policies, while the
behaviour stays the same.


## Problem B

Qualifier keys are converted to lowercase in both parseQualifiers
methods, so the same rule was written in two places.

The two parsing methods should remain separate because they handle
different input and have different behaviour, such as duplicate-key
detection in the encoded-string version.


## Refactoring B

Changes:
- Added canonicalizeQualifierKey.
- Both parseQualifiers methods now use the helper.

Tests after Refactoring B:
- mvn test: PASS
- CS4928Lab2CheckTest: PASS
- git diff --check: PASS

Refactoring B commit SHA:
1c95ca6

Before -> After:
Before, both parseQualifiers methods lowercased qualifier keys
separately. After, both use one helper for the same lowercase rule,
while the two parsing methods remain separate.


## Problem C

Package-type-specific behaviour also exists in other methods such as
validateNamespace, validateVersion and verifyTypeConstraints, but this
was deliberately left unchanged to keep the refactoring small and
focused.


## Final Judgement

I would keep both changes because they make the code easier to maintain
by keeping related rules in one place while preserving the existing
behaviour.

A benefit is that changes to package-name or qualifier-key rules are
more localised.

A trade-off is that Refactoring A introduces extra files and structure
for behaviour that was previously contained in one method.


## Repository

Repository URL:
https://github.com/IljaBSK/cs4928-lab2

Refactoring B commit SHA:
1c95ca6