# Product Authenticator Client

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>


<dependency>
    <groupId>com.github.jaidennnnn</groupId>
    <artifactId>Product-Authenticator-Client</artifactId>
    <version>master-SNAPSHOT</version>
</dependency> 
```

## Example

```java
String key = System.console().readLine("Enter Product Key: ");
Authenticator.checkKey(/*Product Key*/ key, /*Ip Address*/ "auth.dragonfruit.gg", /*Port*/ 25560,
                                /*Product Name*/ "mineral_spigot", () -> {
    // Run app
});
```