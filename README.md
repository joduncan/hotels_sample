hotels_sample
=============
All implementation and test cases were written in Groovy.
I used Groovy 2.3.6, but any recent (>2.2) version of Groovy
should work equally well.

For example compilation/test steps, please see the contents
of the compile_and_run_tests.sh file.


Caveat:
There is no code or test cases for invalid/incomplete inputs.

Because of that, sorting results will be incomplete or incorrect
if you load incomplete data. Additionally, neither groovy as a
whole nor the JsonSlurper parser will complain if you load
incomplete data into a POGO.

Depending on the requirements, I would either add validation code
in the relevant classes, most likely the Location and Hotel classes
primarily, or use a different language/library (such as Java &
Jackson) that may do that sort of stricter validation as part of
the language or library capabilities.
