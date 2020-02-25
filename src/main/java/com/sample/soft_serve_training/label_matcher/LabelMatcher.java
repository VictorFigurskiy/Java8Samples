package com.sample.soft_serve_training.label_matcher;

public class LabelMatcher {

    public static String matchShopwareLabel(String akeneoLabel){
        return AkeneoShopwareLabelEnum.valueOf(akeneoLabel).getShopwareLabel();
    }

    private enum AkeneoShopwareLabelEnum {

        launch_product("Launch Produkt"),
        size_type("Größentyp"),
        gender("Geschlecht"),
        age_group("Altersgruppe"),
        size_eu("Size EU"),
        size_uk("Size UK"),
        size_us("Size US"),
        size_foot_length("Fersen-Zehen-Länge (cm)"),
        size_bust("Brustumfang (cm)"),
        size_waist("Taillenumfang (cm)"),
        size_hip("Hüftumfang (cm)"),
        size_body_height("Körpergröße (cm)"),
        size_leg_length("Beininnenlänge (cm)"),
        size_age_month("Alter (Monate)"),
        size_age_year("Alter (Jahre)"),
        ball_circumference("Ballgröße"),
        collection("Kollektion"),
        manufacturer_color("Herstellerfarbe"),
        color_secondary("Farbe (sekundär)"),
        material_outside_1("Material 1"),
        material_outside_1_amount("Material 1 (Anteil)"),
        material_outside_2("Material 2"),
        material_outside_2_amount("Material 2 (Anteil)"),
        material_outside_3("Material 3"),
        material_outside_3_amount("Material 3 (Anteil)"),
        material_outside_other("Weiteres Material"),
        material_outside_other_amount("Weiteres Material (Anteil)"),
        material_inside("Innenmaterial"),
        lining("Futter"),
        technology("Technologien"),
        material_characteristics("Materialeigenschaften"),
        reflectors("Reflektoren"),
        reflectors_placement("Reflektorenplatzierung"),
        textile_tops_fit("Schnitt"),
        delivery_content("Zusätzlicher Lieferumfang"),
        shoe_material_outer_sole("Material Außensohle"),
        shoe_material_inner_sole("Material Innensohle"),
        shoe_fastening("Verschluss"),
        shoe_shank_height("Schafthöhe"),
        shoe_weight("Schuhgewicht"),
        shoe_weight_underlying_size_eu("Schuhgewicht / Schuhgröße (EU)"),
        shoe_inner_sole_removable("Herausnehmbare Innensohle"),
        shoe_running_heel_lift("Sprengung"),
        shoe_running_cushioning("Abfederung"),
        shoe_running_pronation_support("Pronationsstütze"),
        shoe_sole_profile("Sohlenprofil"),
        shoe_sneaker_height("Sneaker Höhe"),
        weather_resistance("Wetterfestigkeit"),
        textile_parts_of_animals("Nichttextile Teile tierischen Ursprungs"),
        care_instructions("Pflegehinweise"),
        sports_club("Verein"),
        sports_season("Spielzeit"),
        temperature_range("Temperaturbereich"),
        textile_fit_hint("Hinweis Passform"),
        textile_tops_collar("Kragen"),
        textile_tops_cuffs("Ärmelbündchen"),
        textile_tops_fashionable_details("Modische Details"),
        textile_tops_fastening("Verschluss"),
        textile_tops_fitting("Passform"),
        textile_tops_length("Länge"),
        textile_tops_neckline("Ausschnitt"),
        textile_tops_performance_extras("Extras"),
        textile_tops_pockets("Taschen"),
        textile_tops_sleeves_length("Ärmellänge"),
        textile_tops_ventilation_zone("Ventilationszonen"),
        optic("Optik"),
        textile_tops_back_length("Rückenlänge"),
        textile_tops_back_length_underlying_textile_size("Rückenlänge / Größe"),
        textile_tops_hood("Kapuze"),
        textile_tops_hood_type("Kapuzeart"),
        textile_tops_sleeves_removable("Abnehmbare Ärmel"),
        textile_tops_zipper("Zipper"),
        textile_tops_zipper_pocket("Reißverschlusstasche"),
        textile_tops_jersey_variant("Trikotvariante"),
        sports_player_basketball("Spieler Basektball"),
        textile_tops_neckline_jersey("Ausschnitt Trikot"),
        textile_tops_jackets_type("Jacke Typ"),
        features_textiles("Funktionen Textilien"),
        sportsjacket_type("Sportjacken Art"),
        textile_tops_windstopper("Windstopper"),
        textile_tops_windproof("Winddicht"),
        textile_pants_waistband("Hosenbund"),
        textile_pants_leg_length("Beinlänge"),
        textile_pants_fly("Hosenschlitz"),
        textile_pants_ventilation_zone("Ventilationszonen"),
        textile_pants_insert("Einsatz"),
        textile_pants_inside_leg_length_underlying_size("Beininnenlänge / Größe"),
        textile_pants_inside_leg_length("Beininnenlänge"),
        textile_pants_waistline("Leibhöhe"),
        textile_pants_adjustable_waistband("Verstellbarer Bund"),
        textile_pants_leg_gathers("Beinabschluss"),
        textile_pants_compression("Kompression"),
        textile_pants_fitness_pants_length("Fitness Hosen Länge"),
        textile_pants_zipper_pocket("Reißverschlusstasche"),
        textile_pants_performance_extras("Extras"),
        textile_bra_fastening("Verschluss"),
        textile_bra_support("Support"),
        textile_bra_underwire("Bügel"),
        textile_bra_cupdetails("Cupdetails"),
        textile_bra_strap_adjustable("Verstellbare Träger"),
        textile_socks_height("Sockenhöhe"),
        textile_socks_compression("Kompression"),
        ball_bladder("Blase"),
        ball_certification("Zertifizierung"),
        ball_type("Balltyp"),
        ball_delivery_condition("Lieferzustand"),
        gloves_fastening("Verschluss"),
        gloves_extras_gloves("Extras Handschuhe"),
        bag_volume("Volumen"),
        bag_height("Höhe"),
        bag_depth("Tiefe"),
        bag_width("Breite"),
        bag_training_bottom_compartment("Schuhfach"),
        bag_backpack_laptop_compartment("Laptopfach"),
        bag_fastening("Verschluss"),
        bag_inside_compartments("Fächer"),
        bag_outside_compartments("Taschenaufteilung"),
        cap_style("Style"),
        cap_peak("Verschluss"),
        cap_adjustable("Verstellbar"),
        number_of_parts("Anzahl Teile"),
        packaging_size("Verpackungseinheit"),
        lining_textile("Futter Textil"),
        material_filling("Material Füllung"),
        underground("Untergrund"),
        basketball_team("Baskteballteam"),
        teamsportarticle("Teamsportartikel"),
        period_of_validity("Laufzeit"),
        team_collection("Serie"),
        amount_of_balls("Anzahl Bälle"),
        association("Verband"),
        application("Einsatzbereich"),
        shoe_running_stability("Stabilität")
        ;

        private String shopwareLabel;

        AkeneoShopwareLabelEnum(String shopwareLabel) {
            this.shopwareLabel = shopwareLabel;
        }

        public String getShopwareLabel() {
            return shopwareLabel;
        }
    }

}
