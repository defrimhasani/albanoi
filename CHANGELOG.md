# Changelog

## Unreleased — 0.0.3-SNAPSHOT

- Consolidate module versions and dependency management in a root Maven reactor.
- Keep Java 17 compatibility and update the Spring Boot baseline from 3.0.1 to 3.5.16.
- Add a Maven 3.9.14 wrapper and explicit modern build/test plugins.
- Use Spring Boot's `@AutoConfiguration` and allow applications to supply their own gateway bean.
- Make `CommandResult.noResult()` type-safe without changing its erased API signature.
- Add tests for auto-configuration discovery, custom gateways, dispatch, missing/duplicate handlers, result-type matching, and empty command results.
- Preserve Java parameter names for Spring MVC and cover sample POST/GET endpoints with HTTP integration tests.
- Refresh CI, validate documentation on pull requests, and add Dependabot updates.
- Replace retired OSSRH/JReleaser configuration with a manual Central Portal publication profile.
- Correct setup instructions, dependency versions, and README examples.
- Correct swapped command/query blog examples while preserving their URLs.

## 0.0.2

Existing published version of the commands, queries, and Spring Boot starter artifacts on Maven Central; Git tag `v0.0.2`.
