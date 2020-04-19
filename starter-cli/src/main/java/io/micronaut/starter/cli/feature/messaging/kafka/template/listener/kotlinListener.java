package io.micronaut.starter.cli.feature.messaging.kafka.template.listener;

import java.io.IOException;

import com.fizzed.rocker.RenderingException;
import com.fizzed.rocker.runtime.DefaultRockerTemplate;
// import @ [1:1]
import io.micronaut.starter.Project;

/*
 * Auto generated code to render template io/micronaut/starter/feature/messaging/kafka/template/listener/kotlinListener.rocker.raw
 * Do not edit this file. Changes will eventually be overwritten by Rocker parser!
 */
@SuppressWarnings("unused")
public class kotlinListener extends com.fizzed.rocker.runtime.DefaultRockerModel {

    static public com.fizzed.rocker.ContentType getContentType() { return com.fizzed.rocker.ContentType.RAW; }
    static public String getTemplateName() { return "kotlinListener.rocker.raw"; }
    static public String getTemplatePackageName() { return "io.micronaut.starter.feature.messaging.kafka.template.listener"; }
    static public String getHeaderHash() { return "-1654727492"; }
    static public long getModifiedAt() { return 1587062563000L; }
    static public String[] getArgumentNames() { return new String[] { "project" }; }

    // argument @ [3:2]
    private Project project;

    public kotlinListener project(Project project) {
        this.project = project;
        return this;
    }

    public Project project() {
        return this.project;
    }

    static public kotlinListener template(Project project) {
        return new kotlinListener()
            .project(project);
    }

    @Override
    protected DefaultRockerTemplate buildTemplate() throws RenderingException {
        // optimized for convenience (runtime auto reloading enabled if rocker.reloading=true)
        return com.fizzed.rocker.runtime.RockerRuntime.getInstance().getBootstrap().template(this.getClass(), this);
    }

    static public class Template extends com.fizzed.rocker.runtime.DefaultRockerTemplate {

        //     package 
        static private final String PLAIN_TEXT_0_0 = "    package ";
        // \n
        static private final String PLAIN_TEXT_1_0 = "\n";
        // import io.micronaut.configuration.kafka.annotation.KafkaListener\nimport io.micronaut.configuration.kafka.annotation.OffsetReset\n\n@KafkaListener(offsetReset = OffsetReset.EARLIEST)\nclass 
        static private final String PLAIN_TEXT_2_0 = "import io.micronaut.configuration.kafka.annotation.KafkaListener\nimport io.micronaut.configuration.kafka.annotation.OffsetReset\n\n@KafkaListener(offsetReset = OffsetReset.EARLIEST)\nclass ";
        //  {\n\n}
        static private final String PLAIN_TEXT_3_0 = " {\n\n}";

        // argument @ [3:2]
        protected final Project project;

        public Template(kotlinListener model) {
            super(model);
            __internal.setCharset("UTF-8");
            __internal.setContentType(getContentType());
            __internal.setTemplateName(getTemplateName());
            __internal.setTemplatePackageName(getTemplatePackageName());
            this.project = model.project();
        }

        @Override
        protected void __doRender() throws IOException, RenderingException {
            // IfBlockBegin @ [7:1]
            __internal.aboutToExecutePosInTemplate(7, 1);
            if (project.getPackageName() != null) {
                // PlainText @ [7:41]
                __internal.aboutToExecutePosInTemplate(7, 41);
                __internal.writeValue(PLAIN_TEXT_0_0);
                // ValueExpression @ [8:13]
                __internal.aboutToExecutePosInTemplate(8, 13);
                __internal.renderValue(project.getPackageName(), false);
                // PlainText @ [8:38]
                __internal.aboutToExecutePosInTemplate(8, 38);
                __internal.writeValue(PLAIN_TEXT_1_0);
                // IfBlockEnd @ [7:1]
                __internal.aboutToExecutePosInTemplate(7, 1);
            } // if end @ [7:1]
            // PlainText @ [9:2]
            __internal.aboutToExecutePosInTemplate(9, 2);
            __internal.writeValue(PLAIN_TEXT_2_0);
            // ValueExpression @ [15:7]
            __internal.aboutToExecutePosInTemplate(15, 7);
            __internal.renderValue(project.getClassName(), false);
            // PlainText @ [15:30]
            __internal.aboutToExecutePosInTemplate(15, 30);
            __internal.writeValue(PLAIN_TEXT_3_0);
        }
    }

}
