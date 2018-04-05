# java-time-literals

A Clojure library that defines literals for
[java.time](https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html)
classes.

It does this by registering edn tags for reading, and adding handlers for
`clojure.core/print-method`.

## Install

Add `[java-time-literals "2018-04-05"]` to `:dependencies` in your `project.clj`.

## Usage

Require `java-time-literals.core` and start using the literals:

```clj
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

## License

Copyright © (iterate inc 2018) Magnar Sveen

[BSD-3-Clause](http://opensource.org/licenses/BSD-3-Clause), see LICENSE
