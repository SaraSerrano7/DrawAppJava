# DRAWAPP #

## How to use ##

This is the version for Java of DrawApp that works for Windows.
It uses locale language by default, but it can be switched to Spanish, Catalan or English manually.

Actually there are three languages supported:
- English -- 'translations_en'
- Spanish -- 'translations_es'
- Catalan -- 'translations_ca'

To switch easily between languages, line 19:

    static final ResourceBundle i18n
            = ResourceBundle.getBundle("i18n.<language>");

must be changed, where `<language>` is one of the three languages mentioned before.
If `<language>` is written as 'translations', it will use the user's locale language. 

When the program is executed, you must answer 3 questions:
- The shape you want to draw (line, square or circle)
- The color you want your figure to be (red, green or blue)
- The size you want your figure to be (little, medium or big)

Then, the figure drawn is shown.

## How internationalization has been done ##

- `i18n` package created, with a properties file for each supported language. 
- Function t() created, which receives the String to translate and returns its translation. 
- Every string hardcoded in `main` has been surrounded by t().
- Inspection for hardcoded strings activated, and each highlighted String has been added to the Resource Bundle for its translation.
- Translations written for each supported language.