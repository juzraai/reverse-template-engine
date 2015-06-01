# Reverse Template Engine
Template based text parsing ;-)

<img hspace="10" src="http://img.shields.io/badge/status-planning-blue.svg" />

## Concept

RTE wants to be a universal solution to parse structured texts. It needs two input:
* the text you want to parse
* and the template, which defines patterns, places of output variables and controls the parser algorythm.

In the end it will produce a JSON-like output with the variables and their results.



## Expectations

* parsing
  * the main thing: capture groups and put the matched value into a variable
  * handle optional input lines
  * handle multiline input blocks
  * handle repeating input lines
  * handle repeating input blocks - be able to build a list
  * handle recursive blocks - "list in list" problem
  * maybe some extra parsing on captured values (replace, cast)
* output
  * JSON-like output
  * mechanism to fill a POJO structure
  * mechanism to list used properties from template, and also verify a potential result POJO class