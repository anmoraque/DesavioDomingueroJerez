# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.OpenSSLProvider
# 1. MANTENER CLASE DE MODELO 'Negocios' (RUTA EXACTA)
-keep class com.anmoraque.eldesaviodominguerojerez.model.Negocios {
    # Mantiene todos los campos y métodos de esta clase,
    # incluyendo los anotados con @SerializedName, para que Gson funcione.
    <fields>;
    <methods>;
}

# 2. MANTENER EL CONSTRUCTOR USADO POR GSON
# Gson usa reflexión, así que a veces necesita que se mantenga el constructor vacío,
# o el constructor que usa para la deserialización.
# Es buena práctica mantener todos los constructores de una clase de modelo.
-keep class com.anmoraque.eldesaviodominguerojerez.model.Negocios {
    public <init>(...);
}
# 3. REGLAS NECESARIAS PARA GSON
# Mantiene las anotaciones (como @SerializedName) y las firmas genéricas
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-dontwarn sun.misc.Unsafe

# Reglas generales para el funcionamiento interno de Gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
# 4. REGLAS PARA GLIDE (Librería de Imágenes)
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.VideoDecoder$DecodedFormat { *; }
-keep class com.bumptech.glide.load.data.ContentResolverStreamFetcher$* { *; }