(ns culture.facts
  "Country-level regional-culture catalog for Italy (ITA) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"ITA"
   [{:culture/id "ita.dish.pizza"
     :culture/name "Pizza"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Italian dish from Naples; Raffaele Esposito is often credited with creating the modern version in Naples at the end of the 19th century."
     :culture/url "https://en.wikipedia.org/wiki/Pizza"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.dish.spaghetti"
     :culture/name "Spaghetti"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Long, thin, solid, cylindrical pasta, a staple food of Italian cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Spaghetti"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.dish.gelato"
     :culture/name "Gelato"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Type of ice cream of Italian origin."
     :culture/url "https://en.wikipedia.org/wiki/Gelato"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.dish.tiramisu"
     :culture/name "Tiramisu"
     :culture/name-local "Tiramisù"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Italian dessert originating in northeastern Italy (Veneto and Friuli-Venezia Giulia regions)."
     :culture/url "https://en.wikipedia.org/wiki/Tiramisu"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.beverage.espresso"
     :culture/name "Espresso"
     :culture/country "ITA"
     :culture/kind :beverage
     :culture/summary "Coffee-brewing method originating in Italy, now one of the most popular worldwide."
     :culture/url "https://en.wikipedia.org/wiki/Espresso"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.beverage.chianti"
     :culture/name "Chianti"
     :culture/country "ITA"
     :culture/kind :beverage
     :culture/summary "Red wine produced in the Chianti region of central Tuscany, holding DOCG classification."
     :culture/url "https://en.wikipedia.org/wiki/Chianti"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.product.parmigiano-reggiano"
     :culture/name "Parmigiano Reggiano"
     :culture/country "ITA"
     :culture/kind :product
     :culture/summary "Hard, granular cow's-milk cheese aged at least 12 months, with protected designation of origin (PDO) status."
     :culture/url "https://en.wikipedia.org/wiki/Parmigiano_Reggiano"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.craft.murano-glass"
     :culture/name "Murano glass"
     :culture/country "ITA"
     :culture/kind :craft
     :culture/summary "Glassware made in Venice, typically on the island of Murano, with elaborate decoration and techniques developed over centuries."
     :culture/url "https://en.wikipedia.org/wiki/Murano_glass"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.festival.carnival-of-venice"
     :culture/name "Carnival of Venice"
     :culture/name-local "Carnevale di Venezia"
     :culture/country "ITA"
     :culture/kind :festival
     :culture/summary "Annual festival held in Venice, famous for its elaborate costumes and masks."
     :culture/url "https://en.wikipedia.org/wiki/Carnival_of_Venice"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ita.heritage.venice"
     :culture/name "Venice and its Lagoon"
     :culture/name-local "Venezia"
     :culture/country "ITA"
     :culture/kind :heritage
     :culture/summary "Venice and its lagoon, inscribed as a UNESCO World Heritage Site in 1987."
     :culture/url "https://en.wikipedia.org/wiki/Venice"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-ita culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "ITA"))
                 " ITA entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
