# Releasing Albanoi

The working tree is `0.0.3-SNAPSHOT`. No new release is published by a normal build, CI run, or push. Published Maven Central versions are immutable; use a new version rather than attempting to replace `0.0.2`.

## Prerequisites

- Access to the `com.defrimhsn` namespace in the [Sonatype Central Portal](https://central.sonatype.com).
- A Central Portal user token (not the retired OSSRH credentials).
- A GPG signing key with its public key available to Maven Central validation.
- Java 17+ and the repository's Maven wrapper.

Store the token outside Git, in your Maven `settings.xml`:

```xml
<settings>
    <servers>
        <server>
            <id>central</id>
            <username>${env.CENTRAL_TOKEN_USERNAME}</username>
            <password>${env.CENTRAL_TOKEN_PASSWORD}</password>
        </server>
    </servers>
</settings>
```

Use your local GPG agent for signing. Never commit tokens, private keys, or passphrases.

## Validate without publishing

```sh
./mvnw clean verify
./mvnw -Ppublication -Dgpg.skip=true \
  -pl albanoi-commands,albanoi-queries,albanoi-spring-boot-starter -am verify
```

The second command checks the publication profile and generates source/Javadoc JARs, but does not sign or upload anything.

## Prepare and publish a release

1. Change the root version and all four module parent versions to the chosen release version (for example, `0.0.3`). Keep them in sync.
2. Move the unreleased changelog into a dated release section and update the README, sample JAR path, and documentation dependency example to the release version.
3. Run both validation commands above. Review the generated POMs and artifacts.
4. Commit the release preparation.
5. Sign and upload **only the parent and three library modules**:

   ```sh
   ./mvnw -Ppublication \
     -pl albanoi-commands,albanoi-queries,albanoi-spring-boot-starter -am clean deploy
   ```

   This uses Sonatype's `central-publishing-maven-plugin`. `autoPublish` is deliberately `false`: review and publish the validated deployment manually in the Central Portal. Do not use the sample in the publication reactor.

6. After confirming the artifacts are available on Maven Central, create and push the matching Git tag and publish GitHub release notes.
7. Advance the root/module parent versions to the next `-SNAPSHOT` development version.

The old `s01.oss.sonatype.org` publishing endpoints are no longer used. See [Sonatype's Maven publication guide](https://central.sonatype.org/publish/publish-portal-maven/) for credential and signing requirements.
