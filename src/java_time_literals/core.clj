(ns java-time-literals.core
  (:import java.io.Writer
           [java.time DayOfWeek Duration Instant LocalDate LocalDateTime LocalTime Month MonthDay OffsetDateTime OffsetTime Period Year YearMonth ZonedDateTime ZoneId ZoneOffset]
           [java.time.temporal ChronoField ChronoUnit]))

(comment
  (set! *warn-on-reflection* true))

(defmacro define-tag [tag label class parse-fn-name parse-param-class]
  (let [s (gensym)
        this (gensym)
        w (gensym)]
    `(do
       (defn ~(symbol (str "parse-" label)) {:tag ~class} [~(with-meta s {:tag parse-param-class})]
         (~(symbol (name class) parse-fn-name) ~s))

       (defmethod print-method ~class [~(with-meta this {:tag class}) ~(with-meta w {:tag 'java.io.Writer})]
         (.write ~w ~(str tag " \""))
         (.write ~w (.toString ~this))
         (.write ~w "\""))

       (defmethod print-dup ~class [~(with-meta this {:tag class}) ~(with-meta w {:tag 'java.io.Writer})]
         (.write ~w ~(str "#=(" *ns* "/parse-" label " \""))
         (.write ~w (.toString ~this))
         (.write ~w "\")")))))

(define-tag "#time/dur"     duration          Duration        "parse" CharSequence)
(define-tag "#time/inst"    instant           Instant         "parse" CharSequence)
(define-tag "#time/ld"      local-date        LocalDate       "parse" CharSequence)
(define-tag "#time/ldt"     local-date-time   LocalDateTime   "parse" CharSequence)
(define-tag "#time/lt"      local-time        LocalTime       "parse" CharSequence)
(define-tag "#time/md"      month-day         MonthDay        "parse" CharSequence)
(define-tag "#time/odt"     offset-date-time  OffsetDateTime  "parse" CharSequence)
(define-tag "#time/ot"      offset-time       OffsetTime      "parse" CharSequence)
(define-tag "#time/period"  period            Period          "parse" CharSequence)
(define-tag "#time/y"       year              Year            "parse" CharSequence)
(define-tag "#time/ym"      year-month        YearMonth       "parse" CharSequence)
(define-tag "#time/zdt"     zoned-date-time   ZonedDateTime   "parse" CharSequence)
(define-tag "#time/zid"     zone-id           ZoneId          "of" String)
(define-tag "#time/zoffset" zone-offset       ZoneOffset      "of" String)

(comment
  #time/dur "PT15M"
  #time/inst "2007-12-03T10:15:30.00Z"
  #time/ld "2007-12-03"
  #time/ldt "2007-12-03T10:15:30"
  #time/lt "10:15:30"
  #time/md "--12-03"
  #time/odt "2007-12-03T10:15:30+01:00"
  #time/ot "10:15:30+01:00"
  #time/period "P3M"
  #time/y "2007"
  #time/ym "2007-12"
  #time/zdt "2007-12-03T10:15:30+01:00[Europe/Paris]"
  #time/zid "Europe/Paris"
  #time/zoffset "+02:00")

(defmacro define-enum [tag label class enum-map]
  (let [s (gensym)
        this (gensym)
        w (gensym)
        lookup (gensym)
        reverse-lookup (gensym)]
    `(do
       (def ~lookup ~enum-map)

       (def ~reverse-lookup
         (into {} (map (juxt second first) ~lookup)))

       (defn ~label [kw#]
         (or (~lookup kw#)
             (throw (Exception. (str kw# " is not a constant in enum " ~(str class) )))))

       (defmethod print-method ~class [~this ~(with-meta w {:tag 'java.io.Writer})]
         (.write ~w (str ~tag " "))
         (.write ~w (str (~reverse-lookup ~this))))

       (defmethod print-dup ~class [~(with-meta this {:tag class}) ~(with-meta w {:tag 'java.io.Writer})]
         (.write ~w ~(str "#=(" *ns* "/" label " "))
         (.write ~w (str (~reverse-lookup ~this)))
         (.write ~w ")")))))

(define-enum "#time/unit" chrono-units ChronoUnit
  {:centuries ChronoUnit/CENTURIES
   :days ChronoUnit/DAYS
   :decades ChronoUnit/DECADES
   :eras ChronoUnit/ERAS
   :forever ChronoUnit/FOREVER
   :half-days ChronoUnit/HALF_DAYS
   :hours ChronoUnit/HOURS
   :micros ChronoUnit/MICROS
   :millennia ChronoUnit/MILLENNIA
   :millis ChronoUnit/MILLIS
   :minutes ChronoUnit/MINUTES
   :months ChronoUnit/MONTHS
   :nanos ChronoUnit/NANOS
   :seconds ChronoUnit/SECONDS
   :weeks ChronoUnit/WEEKS
   :years ChronoUnit/YEARS})

(define-enum "#time/field" chrono-fields ChronoField
  {:aligned-day-of-week-in-month ChronoField/ALIGNED_DAY_OF_WEEK_IN_MONTH
   :aligned-day-of-week-in-year ChronoField/ALIGNED_DAY_OF_WEEK_IN_YEAR
   :aligned-week-of-month ChronoField/ALIGNED_WEEK_OF_MONTH
   :aligned-week-of-year ChronoField/ALIGNED_WEEK_OF_YEAR
   :ampm-of-day ChronoField/AMPM_OF_DAY
   :clock-hour-of-ampm ChronoField/CLOCK_HOUR_OF_AMPM
   :clock-hour-of-day ChronoField/CLOCK_HOUR_OF_DAY
   :day-of-month ChronoField/DAY_OF_MONTH
   :day-of-week ChronoField/DAY_OF_WEEK
   :day-of-year ChronoField/DAY_OF_YEAR
   :epoch-day ChronoField/EPOCH_DAY
   :era ChronoField/ERA
   :hour-of-ampm ChronoField/HOUR_OF_AMPM
   :hour-of-day ChronoField/HOUR_OF_DAY
   :instant-seconds ChronoField/INSTANT_SECONDS
   :micro-of-day ChronoField/MICRO_OF_DAY
   :micro-of-second ChronoField/MICRO_OF_SECOND
   :milli-of-day ChronoField/MILLI_OF_DAY
   :milli-of-second ChronoField/MILLI_OF_SECOND
   :minute-of-day ChronoField/MINUTE_OF_DAY
   :minute-of-hour ChronoField/MINUTE_OF_HOUR
   :month-of-year ChronoField/MONTH_OF_YEAR
   :nano-of-day ChronoField/NANO_OF_DAY
   :nano-of-second ChronoField/NANO_OF_SECOND
   :offset-seconds ChronoField/OFFSET_SECONDS
   :proleptic-month ChronoField/PROLEPTIC_MONTH
   :second-of-day ChronoField/SECOND_OF_DAY
   :second-of-minute ChronoField/SECOND_OF_MINUTE
   :year ChronoField/YEAR
   :year-of-era  ChronoField/YEAR_OF_ERA})

(define-enum "#time/month" months Month
  {:january Month/JANUARY
   :february Month/FEBRUARY
   :march Month/MARCH
   :april Month/APRIL
   :may Month/MAY
   :june Month/JUNE
   :july Month/JULY
   :august Month/AUGUST
   :september Month/SEPTEMBER
   :october Month/OCTOBER
   :november Month/NOVEMBER
   :december Month/DECEMBER})

(define-enum "#time/day" days-of-week DayOfWeek
  {:monday DayOfWeek/MONDAY
   :tuesday DayOfWeek/TUESDAY
   :wednesday DayOfWeek/WEDNESDAY
   :thursday DayOfWeek/THURSDAY
   :friday DayOfWeek/FRIDAY
   :saturday DayOfWeek/SATURDAY
   :sunday DayOfWeek/SUNDAY})

