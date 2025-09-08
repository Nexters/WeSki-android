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

# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
##---------------End: proguard configuration for Glide  ----------

##---------------Begin: Okio  ----------
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

##---------------End: Okio  ----------

##---------------Begin: proguard configuration for Retrofit2  ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
##---------------End: proguard configuration for Retrofit2  ----------

##---------------Begin: proguard configuration for okhttp3  ----------
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
##---------------End: proguard configuration for okhttp3  ----------

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*


##---------------Begin: kotlin serialization ----------
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Application rules

# Keep all serializable classes in remote module
-keepclassmembers @kotlinx.serialization.Serializable class com.dieski.remote.** {
    # lookup for plugin generated serializable classes
    *** Companion;
    # lookup for serializable objects
    *** INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep all data classes in data module
-keepclassmembers @kotlinx.serialization.Serializable class com.dieski.data.** {
    *** Companion;
    *** INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# lookup for plugin generated serializable classes
-if @kotlinx.serialization.Serializable class com.dieski.remote.**
-keepclassmembers class com.dieski.remote.<1>$Companion {
    kotlinx.serialization.KSerializer serializer(...);
}

-if @kotlinx.serialization.Serializable class com.dieski.data.**
-keepclassmembers class com.dieski.data.<1>$Companion {
    kotlinx.serialization.KSerializer serializer(...);
}

# Serialization supports named companions but for such classes it is necessary to add an additional rule.
# This rule keeps serializer and serializable class from obfuscation. Therefore, it is recommended not to use wildcards in it, but to write rules for each such class.
-keep class com.dieski.remote.SerializableClassWithNamedCompanion$$serializer {
    *** INSTANCE;
}


-keep class com.dieski.remote.** {
    @kotlinx.serialization.SerialName <fields>;
}

# Keep all response and request models completely
-keep class com.dieski.remote.model.response.** { *; }
-keep class com.dieski.remote.model.request.** { *; }

# Keep Retrofit service interfaces
-keep interface com.dieski.remote.service.** { *; }

# Keep NetworkResult and its subclasses
-keep class com.dieski.remote.model.network.NetworkResult { *; }
-keep class com.dieski.remote.model.network.NetworkResult$* { *; }

# Keep NetworkResult and its subclasses
-keep class com.dieski.domain.exception.WeskiException { *; }
-keep class com.dieski.domain.exception.WeskiException* { *; }

# Keep generic type information for NetworkResult
-keepattributes Signature

##---------------END: kotlin serialization ----------

-dontwarn android.media.LoudnessCodecController$OnLoudnessCodecUpdateListener
-dontwarn android.media.LoudnessCodecController

-dontwarn com.google.firebase.ktx.Firebase
-dontwarn com.google.firebase.ktx.FirebaseKt

##---------------Begin: BuildConfig ----------
# Keep all BuildConfig classes and their fields
-keep class **.BuildConfig { *; }
-keepclassmembers class **.BuildConfig {
    public static <fields>;
}
##---------------End: BuildConfig ----------