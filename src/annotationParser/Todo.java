package annotationParser;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Inherited
public @interface Todo {
   NIVEAU niveau() default NIVEAU.BUG;
   String auteur() default "cysboy";
   String destinataire();
   String commentaire();
}