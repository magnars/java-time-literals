(ns java-time-literals.core
  (:import [java.time
            Duration Instant LocalDate LocalDateTime LocalTime MonthDay
            OffsetDateTime OffsetTime Period Year YearMonth
            ZonedDateTime ZoneId ZoneOffset]))

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
         (.write ~w ~(str "#=(java-time-literals.core/parse-" label " \""))
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
