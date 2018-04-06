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

### Enums

Enum tags are represented with keywords.

```clj
;; ChronoUnit

#time/unit :centuries
#time/unit :days
#time/unit :decades
#time/unit :eras
#time/unit :forever
#time/unit :half-days
#time/unit :hours
#time/unit :micros
#time/unit :millennia
#time/unit :millis
#time/unit :minutes
#time/unit :months
#time/unit :nanos
#time/unit :seconds
#time/unit :weeks
#time/unit :years

;; ChronoField

#time/field :aligned-day-of-week-in-month
#time/field :aligned-day-of-week-in-year
#time/field :aligned-week-of-month
#time/field :aligned-week-of-year
#time/field :ampm-of-day
#time/field :clock-hour-of-ampm
#time/field :clock-hour-of-day
#time/field :day-of-month
#time/field :day-of-week
#time/field :day-of-year
#time/field :epoch-day
#time/field :era
#time/field :hour-of-ampm
#time/field :hour-of-day
#time/field :instant-seconds
#time/field :micro-of-day
#time/field :micro-of-second
#time/field :milli-of-day
#time/field :milli-of-second
#time/field :minute-of-day
#time/field :minute-of-hour
#time/field :month-of-year
#time/field :nano-of-day
#time/field :nano-of-second
#time/field :offset-seconds
#time/field :proleptic-month
#time/field :second-of-day
#time/field :second-of-minute
#time/field :year
#time/field :year-of-era

;; Month

#time/month :january
#time/month :february
#time/month :march
#time/month :april
#time/month :may
#time/month :june
#time/month :july
#time/month :august
#time/month :september
#time/month :october
#time/month :november
#time/month :december

;; DayOfWeek

#time/day :monday
#time/day :tuesday
#time/day :wednesday
#time/day :thursday
#time/day :friday
#time/day :saturday
#time/day :sunday
```

## License

Copyright © (iterate inc 2018) Magnar Sveen

[BSD-3-Clause](http://opensource.org/licenses/BSD-3-Clause), see LICENSE
