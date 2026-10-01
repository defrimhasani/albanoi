# Albanoi documentation

Built with [Docusaurus 2](https://docusaurus.io/). Use Node.js 22+; CI uses Node.js 24.

The documentation dependency migration is deferred; the existing package versions
and lockfile are retained until a replacement can be generated and validated.

```sh
npm ci
npm start
```

Validate and build:

```sh
npm run typecheck
npm run build
```

The production site is generated in `build/`. Pull requests build and typecheck
the documentation without deploying it. Pushes to `main` deploy to the existing
Cloudflare Pages project `albanoi` using `CF_API_TOKEN` and `CF_ACCOUNT_ID` repository secrets.
