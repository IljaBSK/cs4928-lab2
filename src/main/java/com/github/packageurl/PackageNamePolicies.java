package com.github.packageurl;

import com.github.packageurl.internal.StringUtil;

final class PackageNamePolicies {

    private static final PackageNamePolicy PRESERVE =
            value -> value;

    private static final PackageNamePolicy LOWERCASE =
            value -> StringUtil.toLowerCase(value);

    private static final PackageNamePolicy PUB =
            value -> StringUtil.toLowerCase(value).replaceAll("[^a-z0-9_]", "_");

    private static final PackageNamePolicy PYPI =
            value -> StringUtil.toLowerCase(value).replace('_', '-');

    static PackageNamePolicy forType(String type) {
        switch (type) {
            case PackageURL.StandardTypes.APK:
            case PackageURL.StandardTypes.BITBUCKET:
            case PackageURL.StandardTypes.BITNAMI:
            case PackageURL.StandardTypes.COMPOSER:
            case PackageURL.StandardTypes.DEB:
            case PackageURL.StandardTypes.GITHUB:
            case PackageURL.StandardTypes.GOLANG:
            case PackageURL.StandardTypes.HEX:
            case PackageURL.StandardTypes.LUAROCKS:
            case PackageURL.StandardTypes.OCI:
                return LOWERCASE;

            case PackageURL.StandardTypes.PUB:
                return PUB;

            case PackageURL.StandardTypes.PYPI:
                return PYPI;

            default:
                return PRESERVE;
        }
    }

    private PackageNamePolicies() {}
}