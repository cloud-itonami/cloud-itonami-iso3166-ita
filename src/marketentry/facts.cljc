(ns marketentry.facts "Italy market-entry catalog.")
(def catalog
  {"ITA" {:name "Italy"
          :owner-authority "ANAC / Consip MEPA"
          :legal-basis "Codice dei contratti pubblici (D.Lgs. 36/2023)"
          :national-spec "MEPA / National e-procurement + Partita IVA"
          :provenance "https://www.acquistinretepa.it/"
          :required-evidence ["Partita IVA record"
                              "MEPA registration record"
                              "Registro imprese extract"
                              "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / Consip"
          :rep-legal-basis "EU establishment or authorized representative for many procedures"
          :rep-provenance "https://www.acquistinretepa.it/"
          :corporate-number-owner-authority "Agenzia delle Entrate / Camera di Commercio"
          :corporate-number-legal-basis "Partita IVA / Codice Fiscale"
          :corporate-number-provenance "https://www.agenziaentrate.gov.it/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "ESP" {:name "Spain" :owner-authority "PLACSP" :legal-basis "LCSP"
          :national-spec "PLACSP" :provenance "https://contrataciondelestado.es/"
          :required-evidence ["NIF/CIF record" "PLACSP registration record" "Mercantile registry extract" "Authorized-representative record"]}
   "FRA" {:name "France" :owner-authority "PLACE" :legal-basis "Code de la commande publique"
          :national-spec "PLACE" :provenance "https://www.economie.gouv.fr/daj/commande-publique"
          :required-evidence ["SIRET/SIREN record" "PLACE registration record" "TVA record" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
