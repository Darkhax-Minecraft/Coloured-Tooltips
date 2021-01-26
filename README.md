# Coloured Tooltips [![](http://cf.way2muchnoise.eu/285684.svg)](https://minecraft.curseforge.com/projects/coloured-tooltips) [![](http://cf.way2muchnoise.eu/versions/285684.svg)](https://minecraft.curseforge.com/projects/coloured-tooltips)

This mod allows for the colours of tooltips to be changed using a configuration file. The config file can be found in `config/colouredtooltips.cfg`.

[![Nodecraft](https://nodecraft.com/assets/images/logo-dark.png)](https://nodecraft.com/r/darkhax)    
This project is sponsored by Nodecraft. Use code [Darkhax](https://nodecraft.com/r/darkhax) for 30% off your first month of service!

Colours in this mod use ARGB hex strings. These are like normal RGB hex strings, but there are two extra characters at the start which control how transparent something is. If you are not familiar with hex colour codes, I would recommend using the [google colour picker](https://www.google.ca/search?q=RGB+color+picker) to get the colour you want. Once you have a good colour, replace the last six characters of the config file value with the six characters for the colour you want.

This mod will change the colour of all tooltips that use Forge's tooltip rendering code. This includes item tooltips and various GUI tooltips like the names of creative tabs and likely tooltips in the GUIs of other mods. 

This mod is properly labled as client side. It is safe to install on a server, however installing it on a server will have absolutely no benefit. 


## Maven Dependency
If you are using [Gradle](https://gradle.org) to manage your dependencies, add the following into your `build.gradle` file. Make sure to replace the version with the correct one. All versions can be viewed [here](https://maven.blamejared.com/net/darkhax/colouredtooltips/).
```
repositories {

    maven {
    
        url 'https://maven.blamejared.com'
    }
}

dependencies {

    // Example: compile "net.darkhax.colouredtooltips:ColouredTooltips-1.16.4:10.0.2"
    compile "net.darkhax.coins:ColouredTooltips-MCVERSION:PUT_FILE_VERSION_HERE"
}
```

## Jar Signing

As of January 11th 2021 officially published builds will be signed. You can validate the integrity of these builds by comparing their signatures with the public fingerprints.

| Hash   | Fingerprint                                                        |
|--------|--------------------------------------------------------------------|
| MD5    | `12F89108EF8DCC223D6723275E87208F`                                 |
| SHA1   | `46D93AD2DC8ADED38A606D3C36A80CB33EFA69D1`                         |
| SHA256 | `EBC4B1678BF90CDBDC4F01B18E6164394C10850BA6C4C748F0FA95F2CB083AE5` |


## Sponsors
<img src="https://nodecraft.com/assets/images/logo-dark.png" width="384" height="90">

This project is sponsored by Nodecraft. Use code [Darkhax](https://nodecraft.com/r/darkhax) for 30% off your first month of service!