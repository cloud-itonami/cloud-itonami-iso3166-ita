(ns statute.facts
  "General-law compliance catalog for Italy (ITA) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/-fra/-can/-aus/-kor/-nld's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL gazzettaufficiale.it (Italian Official
  Gazette) URL -- never fabricated. A law not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an id/url.
  Title for every entry below was directly WebFetch-verified against
  the live gazzettaufficiale.it page on 2026-07-15 -- normattiva.it
  (the consolidated-text portal, usually the more common citation) was
  tried first but returned an internal error page for this query
  pattern, so gazzettaufficiale.it (the original-publication portal,
  also fully official) was used instead.")

(def catalog
  "iso3 -> vector of statute entries."
  {"ITA"
   [{:statute/id "ita.codice-civile"
     :statute/title "Codice civile (Regio Decreto 16 marzo 1942, n. 262)"
     :statute/jurisdiction "ITA"
     :statute/kind :law
     :statute/law-number "R.D. 262/1942"
     :statute/url "https://www.gazzettaufficiale.it/atto/serie_generale/caricaDettaglioAtto/originario?atto.dataPubblicazioneGazzetta=1942-04-04&atto.codiceRedazionale=042U0262"
     :statute/url-provenance :official-gazzetta-ufficiale
     :statute/enacted-date "1942-03-16"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "ita.codice-privacy"
     :statute/title "Codice in materia di protezione dei dati personali (Decreto Legislativo 30 giugno 2003, n. 196)"
     :statute/jurisdiction "ITA"
     :statute/kind :law
     :statute/law-number "D.Lgs. 196/2003"
     :statute/url "https://www.gazzettaufficiale.it/atto/serie_generale/caricaDettaglioAtto/originario?atto.dataPubblicazioneGazzetta=2003-07-29&atto.codiceRedazionale=003G0218"
     :statute/url-provenance :official-gazzetta-ufficiale
     :statute/enacted-date "2003-06-30"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "ita.statuto-dei-lavoratori"
     :statute/title "Statuto dei lavoratori (Legge 20 maggio 1970, n. 300)"
     :statute/jurisdiction "ITA"
     :statute/kind :law
     :statute/law-number "L. 300/1970"
     :statute/url "https://www.gazzettaufficiale.it/eli/id/1970/05/27/070U0300/sg"
     :statute/url-provenance :official-gazzetta-ufficiale
     :statute/enacted-date "1970-05-20"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-ita statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "ITA")) " ITA statutes seeded with an "
                 "official gazzettaufficiale.it citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
