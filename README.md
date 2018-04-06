# java-time-literals

A Clojure library that defines literals for
[java.time](https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html)
classes.

It does this by registering edn tags for reading, and adding handlers for
`clojure.core/print-method` and `clojure.core/print-dup`.

## Install

Add `[java-time-literals "2018-04-05"]` to `:dependencies` in your `project.clj`.

## Usage

Require the library and start using literals:

```clj
(require 'java-time-literals.core)

#time/dur "PT15M" ;; Duration
#time/inst "2007-12-03T10:15:30.00Z" ;; Instant
#time/ld "2007-12-03" ;; LocalDate
#time/ldt "2007-12-03T10:15:30" ;; LocalDateTime
#time/lt "10:15:30" ;; LocalTime
#time/md "--12-03" ;; MonthDay
#time/odt "2007-12-03T10:15:30+01:00" ;; OffsetDateTime
#time/ot "10:15:30+01:00" ;; OffsetTime
#time/period "P3M" ;; Period
#time/y "2007" ;; Year
#time/ym "2007-12" ;; YearMonth
#time/zdt "2007-12-03T10:15:30+01:00[Europe/Paris]" ;; ZonedDateTime
#time/zid "Europe/Paris" ;; ZoneId
#time/zoffset "+02:00" ;; ZoneOffset
```

You'll notice that most of these are quite short. The idea is that you're using
literals to keep things terse. Readability comes as much from the format string
as the tag name.

### Require vs injection

If you want to use these literals in your tests, but don't have a natural main
function or entry point to hold the require, you might find yourself requiring
the namespace again and again. Instead, you can add an injection to your
`project.clj`:

```clj
:injections [(require 'java-time-literals.core)]
```

## License

Copyright © (iterate inc 2018) Magnar Sveen

[BSD-3-Clause](http://opensource.org/licenses/BSD-3-Clause), see LICENSE
