import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * 公共方法
 */
public class FreeMarkerUtil {

    private File templateDir;

    public FreeMarkerUtil(File templateDir) {
        this.templateDir = templateDir;
    }

    public String doTest(String templateFileName, Object dataModel)
            throws IOException, TemplateException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);

        cfg.setDirectoryForTemplateLoading(templateDir);
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate(templateFileName);

        StringWriter stringWriter = new StringWriter();
        template.process(dataModel, stringWriter);

        return stringWriter.toString();

    }
}
