-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

-dontwarn net.sqlcipher.**
-keep class net.sqlcipher.** { *; }
-dontwarn org.conscrypt.**
-keep class org.conscrypt.** { *; }


##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}


##---------------Begin: proguard configuration for Retrofit ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

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

-keepnames class kotlinx.** { *; }

-keep class com.notsatria.core.data.repository.UserPreferenceRepository {
    kotlinx.coroutines.flow.Flow getUserLoginStatus();
}

-keep class com.notsatria.core.utils.ExtensionsKt { *; }

-keep class com.notsatria.core.utils.Resource { *; }

-keep interface com.notsatria.core.ui.MovieAdapterCallback { *; }

-keep class com.notsatria.core.ui.MovieAdapter { *; }

-keep class com.notsatria.core.ui.MovieFavoriteAdapter { *; }

-keep class com.notsatria.core.ui.BaseFragment { *; }

-keep class com.notsatria.core.data.preferences.UserPreferenceKt { *; }

-keep class com.notsatria.core.data.repository.UserPreferenceRepository { *; }

# Keep the entire domain and use case classes to avoid obfuscation or method removal
-keep class com.notsatria.core.domain.usecase.MovieUseCase { *; }
-keep class com.notsatria.core.domain.repository.IMovieRepository { *; }

-keep class kotlinx.coroutines.flow.* { *; }
-keepclassmembers class kotlinx.coroutines.flow.Flow { *; }
-keepclassmembers class kotlinx.coroutines.CoroutineScope { *; }


##---------------Begin: proguard configuration for Glide ----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
<init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
**[] $VALUES;
public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
*** rewind();
}

# Keep Hilt components
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class * implements dagger.hilt.internal.GeneratedComponentManager { *; }
-keep class * implements dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# Keep classes with @Inject constructors
-keepclasseswithmembernames class * {
    @javax.inject.Inject <init>(...);
}

# Keep Hilt Android classes (for projects using Hilt with Android)
-keep class dagger.hilt.android.** { *; }
-keep interface dagger.hilt.android.** { *; }

# Keep classes annotated with @HiltAndroidApp, @AndroidEntryPoint, etc.
-keep @dagger.hilt.android.HiltAndroidApp class *
-keep @dagger.hilt.android.AndroidEntryPoint class *
-keep @dagger.hilt.InstallIn class *
-keep @dagger.Module class *
-keep @dagger.hilt.components.SingletonComponent class *
-keep @dagger.hilt.android.components.ActivityComponent class *
-keep @dagger.hilt.android.components.FragmentComponent class *

# Keep classes with @Module annotations
-keep @dagger.Module class * { *; }

# Keep classes with @Provides annotations
-keep class * {
    @dagger.Provides <methods>;
}

# For instrumentation test
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn java.lang.instrument.Instrumentation
-dontwarn javax.tools.JavaCompiler
-dontwarn javax.tools.ToolProvider
-dontwarn net.bytebuddy.ByteBuddy
-dontwarn net.bytebuddy.ClassFileVersion
-dontwarn net.bytebuddy.TypeCache$SimpleKey
-dontwarn net.bytebuddy.TypeCache$Sort
-dontwarn net.bytebuddy.TypeCache$WithInlineExpunction
-dontwarn net.bytebuddy.TypeCache
-dontwarn net.bytebuddy.agent.ByteBuddyAgent
-dontwarn net.bytebuddy.asm.Advice$AllArguments
-dontwarn net.bytebuddy.asm.Advice$Argument
-dontwarn net.bytebuddy.asm.Advice$Enter
-dontwarn net.bytebuddy.asm.Advice$OnMethodEnter
-dontwarn net.bytebuddy.asm.Advice$OnMethodExit
-dontwarn net.bytebuddy.asm.Advice$OnNonDefaultValue
-dontwarn net.bytebuddy.asm.Advice$Origin
-dontwarn net.bytebuddy.asm.Advice$Return
-dontwarn net.bytebuddy.asm.Advice$This
-dontwarn net.bytebuddy.asm.Advice$WithCustomMapping
-dontwarn net.bytebuddy.asm.Advice
-dontwarn net.bytebuddy.asm.AsmVisitorWrapper$AbstractBase
-dontwarn net.bytebuddy.asm.AsmVisitorWrapper$ForDeclaredMethods$MethodVisitorWrapper
-dontwarn net.bytebuddy.asm.AsmVisitorWrapper$ForDeclaredMethods
-dontwarn net.bytebuddy.asm.AsmVisitorWrapper
-dontwarn net.bytebuddy.description.ByteCodeElement$TypeDependant
-dontwarn net.bytebuddy.description.field.FieldList
-dontwarn net.bytebuddy.description.method.MethodDescription$ForLoadedMethod
-dontwarn net.bytebuddy.description.method.MethodDescription$InDefinedShape
-dontwarn net.bytebuddy.description.method.MethodDescription$SignatureToken
-dontwarn net.bytebuddy.description.method.MethodDescription
-dontwarn net.bytebuddy.description.method.MethodList
-dontwarn net.bytebuddy.description.method.ParameterDescription
-dontwarn net.bytebuddy.description.method.ParameterList
-dontwarn net.bytebuddy.description.modifier.ModifierContributor$ForField
-dontwarn net.bytebuddy.description.modifier.ModifierContributor$ForMethod
-dontwarn net.bytebuddy.description.modifier.Ownership
-dontwarn net.bytebuddy.description.modifier.SynchronizationState
-dontwarn net.bytebuddy.description.modifier.Visibility
-dontwarn net.bytebuddy.description.type.TypeDescription$ForLoadedType
-dontwarn net.bytebuddy.description.type.TypeDescription
-dontwarn net.bytebuddy.dynamic.ClassFileLocator$Simple
-dontwarn net.bytebuddy.dynamic.ClassFileLocator
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$FieldDefinition$Optional$Valuable
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$FieldDefinition$Optional
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ExceptionDefinition
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ImplementationDefinition$Optional
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ImplementationDefinition
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ParameterDefinition$Initial
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ReceiverTypeDefinition
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition
-dontwarn net.bytebuddy.dynamic.DynamicType$Builder
-dontwarn net.bytebuddy.dynamic.DynamicType$Loaded
-dontwarn net.bytebuddy.dynamic.DynamicType$Unloaded
-dontwarn net.bytebuddy.dynamic.Transformer$ForMethod
-dontwarn net.bytebuddy.dynamic.Transformer
-dontwarn net.bytebuddy.dynamic.loading.ClassInjector$UsingLookup
-dontwarn net.bytebuddy.dynamic.loading.ClassInjector$UsingReflection
-dontwarn net.bytebuddy.dynamic.loading.ClassLoadingStrategy$Configurable
-dontwarn net.bytebuddy.dynamic.loading.ClassLoadingStrategy$Default
-dontwarn net.bytebuddy.dynamic.loading.ClassLoadingStrategy$UsingLookup
-dontwarn net.bytebuddy.dynamic.loading.ClassLoadingStrategy
-dontwarn net.bytebuddy.dynamic.loading.MultipleParentClassLoader$Builder
-dontwarn net.bytebuddy.dynamic.loading.MultipleParentClassLoader
-dontwarn net.bytebuddy.dynamic.scaffold.MethodGraph$Compiler$Default
-dontwarn net.bytebuddy.dynamic.scaffold.MethodGraph$Compiler$ForDeclaredMethods
-dontwarn net.bytebuddy.dynamic.scaffold.MethodGraph$Compiler
-dontwarn net.bytebuddy.dynamic.scaffold.MethodGraph$Linked
-dontwarn net.bytebuddy.dynamic.scaffold.MethodGraph$Node$Sort
-dontwarn net.bytebuddy.dynamic.scaffold.MethodGraph$Node
-dontwarn net.bytebuddy.dynamic.scaffold.MethodGraph
-dontwarn net.bytebuddy.dynamic.scaffold.TypeValidation
-dontwarn net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy$Default
-dontwarn net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
-dontwarn net.bytebuddy.implementation.FieldAccessor$OwnerTypeLocatable
-dontwarn net.bytebuddy.implementation.FieldAccessor
-dontwarn net.bytebuddy.implementation.Implementation$Composable
-dontwarn net.bytebuddy.implementation.Implementation$Context$Disabled$Factory
-dontwarn net.bytebuddy.implementation.Implementation$Context$Factory
-dontwarn net.bytebuddy.implementation.Implementation$Context
-dontwarn net.bytebuddy.implementation.Implementation
-dontwarn net.bytebuddy.implementation.MethodCall$WithoutSpecifiedTarget
-dontwarn net.bytebuddy.implementation.MethodCall
-dontwarn net.bytebuddy.implementation.MethodDelegation$WithCustomProperties
-dontwarn net.bytebuddy.implementation.MethodDelegation
-dontwarn net.bytebuddy.implementation.StubMethod
-dontwarn net.bytebuddy.implementation.attribute.MethodAttributeAppender$Factory
-dontwarn net.bytebuddy.implementation.attribute.MethodAttributeAppender$ForInstrumentedMethod
-dontwarn net.bytebuddy.implementation.attribute.MethodAttributeAppender$NoOp
-dontwarn net.bytebuddy.implementation.bind.annotation.AllArguments
-dontwarn net.bytebuddy.implementation.bind.annotation.Argument
-dontwarn net.bytebuddy.implementation.bind.annotation.BindingPriority
-dontwarn net.bytebuddy.implementation.bind.annotation.FieldValue
-dontwarn net.bytebuddy.implementation.bind.annotation.Origin
-dontwarn net.bytebuddy.implementation.bind.annotation.RuntimeType
-dontwarn net.bytebuddy.implementation.bind.annotation.StubValue
-dontwarn net.bytebuddy.implementation.bind.annotation.SuperCall
-dontwarn net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder$ParameterBinder$ForFixedValue$OfConstant
-dontwarn net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder$ParameterBinder
-dontwarn net.bytebuddy.implementation.bind.annotation.This
-dontwarn net.bytebuddy.implementation.bytecode.assign.Assigner$Typing
-dontwarn net.bytebuddy.jar.asm.ClassVisitor
-dontwarn net.bytebuddy.jar.asm.MethodVisitor
-dontwarn net.bytebuddy.matcher.ElementMatcher$Junction
-dontwarn net.bytebuddy.matcher.ElementMatcher
-dontwarn net.bytebuddy.matcher.ElementMatchers
-dontwarn net.bytebuddy.matcher.FilterableList
-dontwarn net.bytebuddy.pool.TypePool
-dontwarn net.bytebuddy.utility.OpenedClassReader
-dontwarn net.bytebuddy.utility.RandomString
-dontwarn org.mockito.internal.creation.bytebuddy.inject.MockMethodDispatcher