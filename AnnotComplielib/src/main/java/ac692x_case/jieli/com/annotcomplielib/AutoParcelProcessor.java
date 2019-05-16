package ac692x_case.jieli.com.annotcomplielib;

import ac692x_case.jieli.com.annotlib.AutoParcel;
import ac692x_case.jieli.com.annotlib.AutoSingleton;
import ac692x_case.jieli.com.annotlib.ParamAnnot;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


@AutoService(Processor.class)

public final class AutoParcelProcessor extends AbstractProcessor {
    private Filer filer;
    private String tag = getClass().getSimpleName() + "-->";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnv.getFiler();



        System.out.println("AutoParcelProcessor get options-->" + processingEnvironment.getOptions());
        for (Map.Entry<String, String> entry : processingEnvironment.getOptions().entrySet()) {
            System.out.println(tag + "  AutoParcelProcessor get options-->key=" + entry.getKey() + "\tvalue=" + entry.getValue());
        }

        System.out.println(tag + "AutoParcelProcessor get getElementUtils-->" + processingEnvironment.getElementUtils());


        System.out.println(tag + "AutoParcelProcessor get getTypeUtils-->" + processingEnvironment.getTypeUtils());
        System.out.println(tag + "AutoParcelProcessor get getFiler-->" + processingEnvironment.getFiler());
        System.out.println(tag + "AutoParcelProcessor get getLocale-->" + processingEnvironment.getLocale());
        System.out.println(tag + "AutoParcelProcessor get getMessager-->" + processingEnvironment.getMessager());
        System.out.println(tag + "AutoParcelProcessor get getSourceVersion-->" + processingEnvironment.getSourceVersion());

       Properties p=System.getProperties() ;
       for(String name:p.stringPropertyNames()){
           System.out.println(tag + "AutoParcelProcessor get  p-->" +name+"\t"+p.getProperty(name));

       }




    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        System.out.println(tag + "errorRaised   " + roundEnvironment.errorRaised());
        System.out.println(tag + "processingOver   " + roundEnvironment.processingOver());




        Set<Element> elements = new LinkedHashSet<>();
        //       elements.addAll(roundEnvironment.getElementsAnnotatedWith(ParamAnnot.class));
        //      elements.addAll(roundEnvironment.getElementsAnnotatedWith(AutoParcel.class));
        elements.addAll(roundEnvironment.getElementsAnnotatedWith(AutoSingleton.class));
        for (Element e : elements) {

            System.out.println("   Element name-->" + e.getSimpleName());
            System.out.println("  Element  kind-->" + e.getKind());
            System.out.println("   Element  midifiers-->" + e.getModifiers());
            System.out.println("   Element  mirrirs-->" + e.getAnnotationMirrors().toString());
            System.out.println("   Element  getEnclosingElement-->" + e.getEnclosingElement().getSimpleName());
            System.out.println("   Element  getEnclosedElements-->" + e.getEnclosedElements().toString());

            if (e.getAnnotation(ParamAnnot.class) != null) {
                System.out.println("   ParamAnnot  getAnnotation-->" + e.getAnnotation(ParamAnnot.class).value());

            }

            if (e.getKind() == ElementKind.CLASS) {

                TypeElement typeElement = (TypeElement) e;
                PackageElement packageElement = processingEnv.getElementUtils().getPackageOf(e);
                String packagePath = packageElement.getQualifiedName().toString();
                String className = typeElement.getSimpleName().toString();

                try {

                    Filer filer=   processingEnv.getFiler()  ;


                    JavaFileManager.Location location=StandardLocation.CLASS_OUTPUT;

                    FileObject fileObject= filer.getResource(location,packagePath   ,e.getSimpleName()+".class");
                    System.out.println("  -------------------in------------------->"+fileObject.toUri());

//                    FileInputStream fis=new FileInputStream(fileObject.toUri().getPath());
//
//                       System.out.println(fis.available());


                    JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(packagePath + "." + className + "_Sen", typeElement);


                    System.out.println("  -------------------------------------->"+sourceFile.toUri());
                    Writer writer = sourceFile.openWriter();
                    writer.write("package  " + packagePath + ";\n");
                    writer.write("import  " + packagePath + "." + className + ";\n");
                    writer.write("public class " + className  + "_Sen"+ "  { \n");
                    writer.write("\n");
                    writer.append("       public " + className + "  targe;\n");
                    writer.write("\n");
                    writer.append("}");
                    writer.flush();
                    writer.close();


                     location=StandardLocation.SOURCE_OUTPUT;
                    fileObject= filer.getResource(location, ""  ,e.getSimpleName()+"_Sen.java");

                     System.out.println("  ---------------------out----------------->"+fileObject.toUri());



                    location=StandardLocation.PLATFORM_CLASS_PATH;
//                    fileObject= filer.getResource(location, ""  , "AndroidManifest.xml");
//                    fileObject= filer.getResource(location, "res.drawable"  , "box.xml");
                    fileObject= filer.getResource(location, "assets.images"  , "clock_font.png");
                    fileObject= filer.getResource(location, "android"  , "R.class");
                    System.out.println("  ---------------------sys----------------->"+fileObject.toUri());




//                    location=StandardLocation.SOURCE_PATH;
//
//
//                    fileObject= filer.getResource(location, ""  , "test.json");
//                    System.out.println("  ---------------------sour----------------->"+fileObject.toUri());
//



                } catch (IOException e1) {
                    e1.printStackTrace();

                }


            }
        }

//        for(Element e:roundEnvironment.getElementsAnnotatedWith(AutoSingleton.class)){
//
//            System.out.println(" AutoSingleton annotitaion  Element-->"+e.getEnclosingElement().getSimpleName());
//            System.out.println("AutoSingleton  annotitaion  mirrir-->"+e.getAnnotationMirrors().toString());
//            System.out.println("AutoSingleton  annotitaion  kind-->"+e.getKind().toString());
//
//
//
//        }
//
//        if(set.size()<1){
//            return false;
//        }

//        roundEnvironment.processingOver();
//
//        MethodSpec main = MethodSpec.methodBuilder("hello")
//                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                .returns(void.class)
//                .addParameter(String[].class, "args")
//                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//                .build();
//        // HelloWorld class
//        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
//                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                .addMethod(main)
//                .build();
//        try {
//            // build com.example.HelloWorld.java
//            JavaFile javaFile = JavaFile.builder("ac692x_case.jieli.com.myapplication", helloWorld)
//                    .addFileComment(" This codes are generated automatically. Do not modify!")
//                    .build();
//            // write to file
//
//            javaFile.writeTo(filer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("end annotitaion-->" + set.size());


        return true;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new LinkedHashSet<>();
        set.add(AutoParcel.class.getCanonicalName());
        set.add(AutoSingleton.class.getCanonicalName());
        set.add(ParamAnnot.class.getCanonicalName());
        return set;
    }
}