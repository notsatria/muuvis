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