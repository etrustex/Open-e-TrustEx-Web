A toolbox project from ECI for:

* exporting translations - DB to XML
* importing translations - XML to DB
* checking the usage of translations that are stored in the DB with regard to project files

The Java <-> XML mapping is done with JAXB, which generates its classes based on the files
in resources/translation and outputs its results in src-generated.

This is a full, stand-alone Eclipse project which should compile as-is.

How to find the missing translations:

select ltr_key, count(*)
from etx_web_label_translation
group by ltr_key
having count(*)  <> 23