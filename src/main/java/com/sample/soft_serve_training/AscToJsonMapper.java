package com.sample.soft_serve_training;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class AscToJsonMapper {

    public static void main(String[] args) {
        String vmpData = "10050811000001105\t\t\t420566009\t\tAmmonia solution strong 8.698g / Eucalyptus oil 500mg granules\t\t7\t\t\t\t\t\t2\t\t\t\t\t1\t2019-01-10\t2\t\t\t\n" +
                "10051011000001108\t\t\t\t\tGeneric Peptamen Junior powder\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10051211000001103\t\t\t\t1\tHelium 79% / Oxygen 21% cylinders 3000litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2016-12-08\t2\t\t\t\n" +
                "10051311000001106\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 2120litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2016-12-07\t2\t\t\t\n" +
                "10051411000001104\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 420litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2016-12-07\t2\t\t\t\n" +
                "10051611000001101\t\t\t\t1\tOxygen 95% / Carbon dioxide 5% cylinders 7200litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2016-12-13\t2\t\t\t\n" +
                "10051911000001107\t\t\t416572003\t\tPalifermin 6.25mg powder for solution for injection vials\t\t1\t\t\t\t\t\t1\t\t\t\t\t1\t2016-04-14\t1\t1\t415818006\t415818006\n" +
                "10052111000001104\t\t\t388379009\t\tPegfilgrastim 6mg/0.6ml solution for injection pre-filled disposable devices\t\t1\t\t\t\t\t\t9\t\t\t\t\t1\t2007-03-05\t1\t.6\t258773002\t3318611000001103\n" +
                "10063711000001101\t\t\t421038002\t\tBismuth subnitrate powder\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2015-01-27\t2\t\t\t\n" +
                "10063811000001109\t\t\t73454001\t\tDesmopressin 120microgram oral lyophilisates sugar free\t\t1\t\t\t\t\t\t1\t1\t\t\t\t\t\t1\t1\t428673006\t428673006\n" +
                "10063911000001104\t\t\t73454001\t\tDesmopressin 60microgram oral lyophilisates sugar free\t\t1\t\t\t\t\t\t1\t1\t\t\t\t\t\t1\t1\t428673006\t428673006\n" +
                "10064011000001101\t\t\t\t\tEchinacea tablets\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t1\t1\t428673006\t428673006\n" +
                "10064111000001100\t\t\t36411211000001107\t\tEleutherococcus 1g capsules\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t428641000\t428641000\n" +
                "10064211000001106\t\t\t422050005\t\tFerric chloride solution strong BPC 1973\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2016-07-14\t2\t\t\t\n" +
                "10064311000001103\t\t\t\t\tGeneric FSC Magnesium & B6 tablets\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t1\t1\t428673006\t428673006\n" +
                "10064511000001109\t\t\t414547000\t\tIsopropyl alcohol 70% liquid\t\t7\t\t\t\t\t\t1\t\t\t\t\t0\t2006-08-14\t2\t\t\t\n" +
                "10064611000001108\t\t\t36410311000001109\t\tSodium cromoglicate 2% eye drops preservative free\t\t4\t\t\t\t\t\t1\t\t\t1\t\t\t\t2\t\t\t\n" +
                "10064711000001104\t\t\t7947003\t\tAspirin 75mg effervescent tablets\t\t2\t\t\t\t\t\t1\t\t\t\t\t1\t2006-05-30\t1\t1\t428673006\t428673006\n" +
                "10075311000001106\t\t\t424332006\t\tBorax powder\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10075411000001104\t\t\t\t\tGeneric Solpadeine Plus soluble tablets sugar free\t\t7\t\t\t\t\t\t4\t1\t\t\t\t\t\t1\t1\t428673006\t428673006\n" +
                "10075511000001100\t\t\t73572009\t\tMorphine sulfate 10mg/1ml solution for injection pre-filled syringes\tMorphine sulfate 10mg/1ml inj pre-filled syringes\t3\t2013-08-01\tMorphine sulphate 10mg/1ml solution for injection pre-filled syringes\t3\t4\t\t1\t\t\t\t\t1\t2010-05-26\t1\t1\t3318611000001103\t3318611000001103\n" +
                "10075611000001101\t\t\t108605008\t\tSalmeterol 25micrograms/dose inhaler CFC free\t\t1\t2006-08-16\tSalmeterol 25micrograms/actuation inhaler CFC free\t1\t4\t\t9\t\t\t\t1\t\t\t1\t1\t3317411000001100\t3317411000001100\n" +
                "10097111000001108\t\t\t10043811000001100\t\tGlucosamine hydrochloride 750mg tablets\t\t4\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t428673006\t428673006\n" +
                "10097211000001102\t\t\t411532008\t\tInsulin glulisine 100units/ml solution for injection 3ml pre-filled disposable devices\t\t1\t\t\t\t\t\t9\t\t\t\t\t\t\t1\t3\t258773002\t3318611000001103\n" +
                "10097311000001105\t\t\t\t\tSoft polymer wound contact dressing impregnated with silver 10cm x 10cm\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10135011000001101\t\t\t346712003\t\tSterile water inhalation solution 500ml bottles\t\t7\t2009-07-10\tSterile water for inhalation 500ml bottles\t7\t4\t\t2\t\t\t\t\t\t\t3\t\t\t\n" +
                "10135411000001105\t\t\t346712003\t\tSterile water inhalation solution 1litre bottles\t\t7\t2009-07-10\tSterile water for inhalation 1litre bottles\t7\t4\t\t2\t\t\t\t\t\t\t3\t\t\t\n" +
                "10135811000001107\t\t\t346712003\t\tSterile water inhalation solution 1.5litre bottles\t\t7\t2009-07-10\tSterile water for inhalation 1.5litre bottles\t7\t4\t\t2\t\t\t\t\t\t\t3\t\t\t\n" +
                "10140711000001108\t\t\t46123006\t\tAscorbic acid 120mg chewable tablets\t\t1\t\t\t\t\t\t1\t\t\t\t\t1\t2010-12-03\t1\t1\t428673006\t428673006\n" +
                "10140811000001100\t\t\t10685611000001102\t\tCalcium levofolinate 175mg/17.5ml solution for injection vials\tCalcium levofolinate 175mg/17.5ml inj vials\t1\t2009-01-21\tCalcium levofolinate 175mg/17.5ml solution for infusion vials\t1\t4\t\t1\t\t\t\t\t\t\t1\t17.5\t258773002\t415818006\n" +
                "10140911000001105\t\t\t406438001\t\tDaptomycin 350mg powder for solution for infusion vials\t\t1\t2009-01-14\tDaptomycin 350mg powder for solution for injection vials\t1\t4\t\t1\t\t\t\t\t\t\t1\t1\t415818006\t415818006\n" +
                "10141011000001102\t\t\t10043411000001102\t\tGlucosamine sulfate 1g tablets\t\t4\t2013-08-01\tGlucosamine sulphate 1g tablets\t4\t4\t\t1\t\t\t\t\t\t\t1\t1\t428673006\t428673006\n" +
                "10141111000001101\t\t\t\t\tInspiratory muscle training device\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10141211000001107\t\t\t\t\tLeg ulcer wrap 28cm-35cm\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10141311000001104\t\t\t\t\tLeg ulcer wrap 35cm-43cm\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10141411000001106\t\t\t\t\tLeg ulcer wrap 43cm-51cm\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10141511000001105\t\t\t\t\tLeg ulcer wrap 51cm-61cm\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10141611000001109\t\t\t\t\tLeg ulcer wrap above 61cm\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t3\t\t\t\n" +
                "10141711000001100\t\t\t\t1\tLymphoedema garments\t\t7\t\t\t\t\t\t2\t\t\t\t\t\t\t3\t\t\t\n" +
                "10142111000001106\t\t\t421915002\t\tRotigotine 8mg/24hours transdermal patches\t\t1\t2008-07-18\tRotigotine 8mg/24hours patches\t1\t4\t\t1\t\t\t\t\t\t\t1\t1\t419702001\t419702001\n" +
                "10142211000001100\t\t\t96278006\t\tSodium hyaluronate cream\t\t3\t\t\t\t\t\t9\t\t\t\t\t\t\t3\t\t\t\n" +
                "10142311000001108\t\t\t\t\tToiletries foam\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t2\t\t\t\n" +
                "10143411000001105\t\t\t346441008\t\tFish oil liquid\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t1\t5\t258773002\t733015007\n" +
                "10143511000001109\t\t\t\t\tGeneric Neupro transdermal patches starter pack\t\t7\t\t\t\t\t1\t4\t\t\t\t\t1\t2016-02-04\t3\t\t\t\n" +
                "10145211000001105\t\t\t\t\tVancomycin powder\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10147111000001108\t\t\t\t\tAntacid and Oxetacaine oral suspension\t\t7\t2013-05-01\tGeneric Antacid & Oxetacaine oral suspension\t7\t4\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10148311000001106\t\t\t319826005\t\tUrokinase 10,000unit powder for solution for injection vials\tUrokinase 10,000unit pdr for soln for inj vials\t7\t2006-03-07\tUrokinase 10,000unit powder for reconstitution for injection vials\t7\t\t\t1\t\t\t\t\t\t\t1\t1\t415818006\t415818006\n" +
                "10148811000001102\t\t\t319826005\t\tUrokinase 50,000unit powder for solution for injection vials\t\t1\t2006-03-07\tUrokinase 50,000unit powder for reconstitution for injection vials\t1\t\t\t1\t\t\t\t\t1\t2019-02-25\t1\t1\t415818006\t415818006\n" +
                "10149111000001102\t\t\t319826005\t\tUrokinase 100,000unit powder for solution for injection vials\tUrokinase 100,000unit inj vials\t1\t2006-03-07\tUrokinase 100,000unit powder for reconstitution for injection vials\t1\t\t\t1\t\t\t\t\t\t\t1\t1\t415818006\t415818006\n" +
                "10149911000001104\t\t\t319826005\t\tUrokinase 500,000unit powder for solution for injection vials\tUrokinase 500,000unit inj vials\t1\t2006-03-07\tUrokinase 500,000unit powder for reconstitution for injection vials\t1\t\t\t1\t\t\t\t\t\t\t1\t1\t415818006\t415818006\n" +
                "10150311000001106\t\t\t318053000\t\tPotassium canrenoate 200mg/10ml solution for injection ampoules\tPotassium canrenoate 200mg/10ml inj ampoules\t1\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t10\t258773002\t413516001\n" +
                "10150911000001107\t\t\t323404007\t\tBenzathine benzylpenicillin 600,000unit powder and solvent for suspension for injection vials\tBenzathine benzylpenicillin 600,000unit inj vials\t1\t2006-03-07\tBenzathine benzylpenicillin 600,000units powder and solvent for suspension for injection vials\t1\t\t\t1\t\t\t\t\t\t\t1\t1\t415818006\t415818006\n" +
                "10153111000001102\t\t\t323404007\t\tBenzathine benzylpenicillin 1.2million unit powder and solvent for suspension for injection vials\tBenzathine benzylpenicillin 1.2million unit inj vials\t1\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t415818006\t415818006\n" +
                "10153211000001108\t\t\t323404007\t\tBenzathine benzylpenicillin 2.4million unit powder and solvent for suspension for injection vials\tBenzathine benzylpenicillin 2.4million unit inj vials\t1\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t415818006\t415818006\n" +
                "10156011000001106\t\t\t346357009\t\tBupivacaine 50mg/20ml (0.25%) / Adrenaline (base) 100micrograms/20ml (1 in 200,000) solution for injection ampoules\tBupivacaine 50mg/Adrenaline (base) 100microg/20ml inj amp\t7\t2014-07-01\tBupivacaine 50mg/20ml (0.25%) / Adrenaline 100micrograms/20ml (1 in 200,000) solution for injection ampoules\t7\t\t\t1\t\t\t\t\t\t\t1\t20\t258773002\t413516001\n" +
                "10156411000001102\t\t\t421639009\t\tAcetarsol 1g suppositories\t\t1\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t430293001\t430293001\n" +
                "10156511000001103\t\t\t421639009\t\tAcetarsol 360mg suppositories\t\t1\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t430293001\t430293001\n" +
                "10156611000001104\t\t\t\t\tEchinacea root extract 40mg tablets\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2009-12-17\t1\t1\t428673006\t428673006\n" +
                "10156711000001108\t\t\t36411011000001102\t\tFrangula bark 100mg / Taraxacum 100mg tablets\t\t7\t\t\t\t\t\t1\t\t\t\t\t1\t2008-11-21\t1\t1\t428673006\t428673006\n" +
                "10156811000001100\t\t\t\t\tGeneric Lloydspharmacy Valerian Sleep Well tablets\t\t7\t\t\t\t\t\t4\t\t\t\t\t1\t2008-10-27\t1\t1\t428673006\t428673006\n" +
                "10157411000001100\t\t\t346357009\t\tBupivacaine 25mg/5ml (0.5%) / Adrenaline (base) 25micrograms/5ml (1 in 200,000) solution for injection ampoules\tBupivacaine 25mg/5ml/Adrenaline (base) 25microg/5ml inj amp\t7\t2014-07-01\tBupivacaine 25mg/5ml (0.5%) / Adrenaline 25micrograms/5ml (1 in 200,000) solution for injection ampoules\t7\t\t\t1\t\t\t\t\t\t\t1\t5\t258773002\t413516001\n" +
                "10157811000001103\t\t\t346357009\t\tBupivacaine 100mg/20ml (0.5%) / Adrenaline (base) 100micrograms/20ml (1 in 200,000) solution for injection ampoules\tBupivacaine 100mg/Adrenaline (base) 100microg/20ml inj amp\t7\t2014-07-01\tBupivacaine 100mg/20ml (0.5%) / Adrenaline 100micrograms/20ml (1 in 200,000) solution for injection ampoules\t7\t\t\t1\t\t\t\t\t\t\t1\t20\t258773002\t413516001\n" +
                "10158211000001100\t\t\t108956002\t\tL-methionine 500mg tablets\t\t7\t2006-04-07\tL-Methionine 500mg tablets\t7\t\t\t1\t\t\t\t\t1\t2016-11-23\t1\t1\t428673006\t428673006\n" +
                "10159011000001100\t\t\t70379000\t\tSodium chloride 0.9% irrigation solution 500ml bottles\t\t7\t2006-05-26\tSodium chloride 0.9% irrigation fluid 500ml bottles\t7\t4\t\t2\t\t\t\t\t1\t2013-10-01\t3\t\t\t\n" +
                "10159411000001109\t\t\t70379000\t\tSodium chloride 0.9% irrigation solution 250ml bottles\t\t7\t2006-05-26\tSodium chloride 0.9% irrigation fluid 250ml bottles\t7\t4\t\t2\t\t\t\t\t\t\t3\t\t\t\n" +
                "10159811000001106\t\t\t\t\tGeneric Balanced Salt Solution Plus irrigation solution\t\t7\t\t\t\t\t\t4\t\t\t\t\t1\t2015-07-01\t2\t\t\t\n" +
                "10160411000001108\t\t\t70379000\t\tSodium chloride 0.9% inhalation solution 1litre bottles\t\t7\t2009-07-10\tSodium chloride 0.9% for inhalation 1litre bottles\t7\t4\t\t2\t\t\t\t\t\t\t3\t\t\t\n" +
                "10161111000001109\t\t\t70379000\t\tSodium chloride 0.9% inhalation solution 1.5litre bottles\t\t7\t2009-07-10\tSodium chloride 0.9% for inhalation 1.5litre bottles\t7\t4\t\t2\t\t\t\t\t\t\t3\t\t\t\n" +
                "10161211000001103\t\t\t\t1\tGeneric Pulmocare Ready-To-Hang 500ml bottle\t\t7\t\t\t\t\t\t4\t\t\t\t\t\t\t2\t\t\t\n" +
                "10161711000001105\t\t\t404830004\t\tCo-careldopa 5mg/20mg/1ml intestinal gel 100ml cassette\t\t2\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t428672001\t428672001\n" +
                "10164811000001100\t\t\t77731008\t\tAcetylcysteine 100mg granules sachets\t\t1\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t733013000\t733013000\n" +
                "10165411000001101\t\t\t77731008\t\tAcetylcysteine 200mg granules sachets\t\t1\t\t\t\t\t\t1\t\t\t\t\t\t\t1\t1\t733013000\t733013000\n" +
                "10166211000001106\t\t\t\t1\tPeracetic acid 0.35% solution 5litre bottles\t\t7\t2008-05-19\tPeracetic acid 0.35% solution 5litre bottles\t7\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10171111000001100\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 210litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10171511000001109\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 290litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10172411000001100\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 2730litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10172511000001101\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 580litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10172911000001108\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 2900litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10173311000001102\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 10600litres\t\t7\t2006-04-07\tNitrous oxide 50% / Oxygen 50% cylinders 10,600litres\t7\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10173711000001103\t\t\t\t1\tNitrous oxide 50% / Oxygen 50% cylinders 14500litres\t\t7\t2006-04-07\tNitrous oxide 50% / Oxygen 50% cylinders 14,500litres\t7\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10174211000001108\t\t\t\t1\tOxygen cylinders 490litres with integral headset\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10174511000001106\t\t\t\t1\tOxygen cylinders 600litres with integral headset\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10174911000001104\t\t\t\t1\tOxygen cylinders 740litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10175311000001101\t\t\t\t1\tOxygen cylinders 1100litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10175711000001102\t\t\t\t1\tOxygen cylinders 2000litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10176111000001109\t\t\t\t1\tOxygen cylinders 2300litres with integral headset\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10176511000001100\t\t\t\t1\tOxygen cylinders 1440litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10176911000001107\t\t\t\t1\tOxygen cylinders 2100litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10177311000001109\t\t\t\t1\tOxygen cylinders 2600litres with integral headset\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10177711000001108\t\t\t\t1\tOxygen cylinders 5100litres with integral headset\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10178111000001108\t\t\t\t1\tOxygen cylinders 5700litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10178511000001104\t\t\t\t1\tOxygen cylinders 5700litres with integral headset\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10178911000001106\t\t\t\t1\tOxygen cylinders 7300litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10179311000001104\t\t\t\t1\tOxygen cylinders 10600litres\t\t7\t2006-06-02\tOxygen cylinders 10,600litres\t7\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10179911000001103\t\t\t\t1\tAir cylinders 2100litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10180311000001104\t\t\t\t1\tAir cylinders 5200litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10180911000001103\t\t\t\t1\tAir cylinders 6800litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10181311000001109\t\t\t\t1\tAir cylinders 9800litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10181711000001108\t\t\t\t1\tHelium cylinders 300litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n" +
                "10182111000001102\t\t\t\t1\tHelium cylinders 2000litres\t\t7\t\t\t\t\t\t1\t\t\t\t\t\t\t2\t\t\t\n";

        String vtmData = "10028511000001109\t1\tCetomacrogol 1000 + Emulsifying Wax + Liquid paraffin + White soft paraffin\t\t\t\n" +
                "10032411000001105\t\tCamphor + Industrial methylated spirit + Oleic acid\t\t\t\n" +
                "10032711000001104\t1\tChalk + Magnesium carbonate + Magnesium Trisilicate + Sodium Bicarbonate\t\t\t\n" +
                "10035711000001108\t1\tCarbomer 974P\t\t\t\n" +
                "10039011000001108\t\tDichlorobenzyl alcohol + Amylmetacresol\t\t\t\n" +
                "10040711000001104\t\tEphedrine + Chlorphenamine\t\t\t\n" +
                "10040911000001102\t\tEicosapentaenoic acid + Docosahexaenoic acid\t\t\t\n" +
                "10041511000001102\t\tWheat grain\t\t\t\n" +
                "10041611000001103\t1\tWild cherry\t\t\t\n" +
                "10041811000001104\t\tValerian + Lemon balm\t\t\t\n" +
                "10041911000001109\t1\tNicotinamide + Pyridoxine + Riboflavin + Thiamine\t\t\t\n" +
                "10043411000001102\t\tGlucosamine sulfate\t\t\t\n" +
                "10043811000001100\t\tGlucosamine hydrochloride\t\t\t\n" +
                "10044111000001109\t\tGlucose + Lidocaine\t\t\t\n" +
                "10044211000001103\t\tTripotassium dicitratobismuthate\t\t\t\n" +
                "10044811000001102\t\tGuaifenesin + Menthol\t\t\t\n" +
                "10045711000001109\t\tCalcium chloride dihydrate + Potassium chloride + Sodium chloride\t\t\t\n" +
                "10045811000001101\t1\tCalcium chloride + Sodium chloride + Sodium lactate + Potassium chloride\t\t\t\n" +
                "10045911000001106\t\tEmulsifying ointment + Phenoxyethanol\t\t\t\n" +
                "10046111000001102\t\tCamphorated opium + Squill oxymel\t\t\t\n" +
                "10046511000001106\t\tSquill + Ipecacuanha\t\t\t\n" +
                "10046611000001105\t1\tSquill\t\t\t\n" +
                "10046711000001101\t\tSalicylic acid + Mucopolysaccharide polysulfate\t\t\t\n" +
                "10047611000001107\t\tHypochlorite\t\t\t\n" +
                "10049011000001109\t\tSodium valproate\t\t\t\n" +
                "10049111000001105\t\tSomatorelin\t\t\t\n" +
                "10050611000001106\t\tPholcodine + Promethazine\t\t\t\n" +
                "10071011000001106\t\tLevothyroxine\t\t\t\n" +
                "10099000\t\tKetoprofen\t\t\t\n" +
                "10164411000001102\t\tRetinol\t\t\t\n" +
                "10170111000001106\t1\tFresh frozen plasma\t\t\t\n" +
                "10201811000001104\t\tLosartan + Hydrochlorothiazide\t\t\t\n" +
                "10201911000001109\t1\tMalic acid\t\t\t\n" +
                "10202011000001102\t\tMangafodipir\t\t\t\n" +
                "10207611000001107\t\tLauromacrogol 400 + Heparinoid\t\t\t\n" +
                "10257611000001100\t\tSodium phosphate dibasic + Monopotassium phosphate\t\t\t\n" +
                "10258411000001104\t\tHomeopathic sulphur\t\t\t\n" +
                "10258511000001100\t\tHomeopathic calcarea sulphurica\t\t\t\n" +
                "10258611000001101\t\tHomeopathic phytolacca decandra\t\t\t\n" +
                "10258711000001105\t\tHomeopathic calendula officinalis\t\t\t\n" +
                "10258811000001102\t\tHomeopathic arnica montana + Homeopathic urtica urens\t\t\t\n" +
                "10258911000001107\t\tHomeopathic calendula officinalis + Homeopathic hypericum perforatum\t\t\t\n" +
                "10259011000001103\t\tHomeopathic dulcamara + Homeopathic lysimachia nummularia\t\t\t\n" +
                "10312003\t\tPrednisone\t\t\t\n" +
                "10354911000001106\t\tAntithymocyte immunoglobulin (rabbit)\t\t\t\n" +
                "10381211000001107\t1\tInvalid - Hytellose\t\t\t\n" +
                "1039008\t\tMercaptopurine\t\t\t\n" +
                "10404711000001106\t\tHomeopathic pulsatilla nigricans\t\t\t\n" +
                "10404811000001103\t\tHomeopathic passiflora incarnata\t\t\t\n" +
                "10404911000001108\t\tHomeopathic hypericum officianalis\t\t\t\n" +
                "10419811000001101\t\tHigh Purity Factor VIII + von Willebrand factor\t\t\t\n" +
                "10426411000001102\t\tFerric subsulfate\t\t\t\n" +
                "10504007\t\tDoxycycline\t\t\t\n" +
                "10565311000001102\t\tCoumarin\t\t\t\n" +
                "10567911000001106\t\tTree pollen\t\t\t\n" +
                "10568011000001108\t\tGrass pollen\t\t\t\n" +
                "10568711000001105\t\tNorethisterone acetate\t\t\t\n" +
                "10569011000001103\t\tBifidobacterium + Lactobacillus\t\t\t\n" +
                "10569111000001102\t\tLactobacillus\t\t\t\n" +
                "10569211000001108\t\tSt. John's wort\t\t\t\n" +
                "10569311000001100\t\tDried yeast\t\t\t\n" +
                "10569711000001101\t\tFactor VIII Inhibitor Bypassing Fraction\t\t\t\n" +
                "10569811000001109\t1\tEvening primrose oil\t\t\t\n" +
                "10576211000001107\t\tSodium acid phosphate + Potassium dihydrogen phosphate\t\t\t\n" +
                "10584811000001107\t\tSodium DL-3-hydroxybutyrate\t\t\t\n" +
                "10599411000001105\t1\tSodium dichloroisocyanurate\t\t\t\n" +
                "10599511000001109\t\tDronabinol + Cannabidiol\t\t\t\n" +
                "10604811000001100\t\tAcetyl-L-carnitine\t\t\t\n" +
                "10621311000001103\t1\tAmphotericin B (lipid formulation)\t\t\t\n" +
                "10640811000001101\t\tCitronella oil\t\t\t\n" +
                "10641411000001107\t\tFig + Senna\t\t\t\n" +
                "10685611000001102\t\tLevofolinic acid\t\t\t\n" +
                "10712001\t\tGlucagon\t\t\t\n" +
                "10756001\t\tHaloperidol\t\t\t\n" +
                "10820011000001102\t\tLipoic acid\t\t\t\n" +
                "108373009\t\tSibutramine\t\t\t\n" +
                "108379008\t\tRemifentanil\t\t\t\n" +
                "108386000\t\tRisperidone\t\t\t\n" +
                "108393001\t\tDesflurane\t\t\t\n" +
                "108395008\t\tSevoflurane\t\t\t\n" +
                "108397000\t\tFosphenytoin\t\t10042311000001104\t2006-06-05\n" +
                "108400009\t\tTopiramate\t\t\t\n" +
                "108402001\t\tGabapentin\t\t\t\n" +
                "108403006\t\tTiagabine\t\t\t\n" +
                "108406003\t\tZolmitriptan\t\t\t\n" +
                "108408002\t\tNaratriptan\t\t\t\n" +
                "108411001\t\tRizatriptan\t\t\t\n" +
                "108415005\t\tDronabinol\t\t\t\n" +
                "108418007\t\tOndansetron\t\t\t\n" +
                "108421009\t\tDolasetron\t\t\t\n" +
                "108424001\t\tGranisetron\t\t\t\n" +
                "108430001\t\tMirtazapine\t\t\t\n" +
                "108432009\t\tVenlafaxine\t\t\t\n" +
                "108435006\t\tNefazodone\t\t\t\n" +
                "108438008\t\tPimozide\t\t\t\n" +
                "108441004\t\tOlanzapine\t\t\t\n" +
                "108443001\t\tQuetiapine\t\t\t\n" +
                "108446009\t\tMivacurium\t\t10202311000001104\t2006-06-05\n" +
                "108449002\t\tRocuronium\t\t9873411000001104\t2006-06-05\n" +
                "108452005\t\tCisatracurium\t\t9873311000001106\t2006-06-05\n";

        LinkedList<LinkedHashMap<String, String>> vmp = AscToJsonMapper.mapPayloadToFields(vmpData, null);
        LinkedList<LinkedHashMap<String, String>> vtm = AscToJsonMapper.mapPayloadToFields(vtmData, "VTM");
    }

    public static LinkedList<LinkedHashMap<String, String>> mapPayloadToFields(String payload, String fileTypeName) {

        LinkedList<LinkedHashMap<String, String>> objectResultList = new LinkedList<>();

        boolean isEnumContainsFileType = Arrays.stream(FilesMappingEnum.values()).anyMatch(filesMappingEnum -> StringUtils.equalsIgnoreCase(filesMappingEnum.name(), fileTypeName));

        if (StringUtils.isNotBlank(payload) && isEnumContainsFileType) {
            String[] rowsArray = StringUtils.split(payload, "\n");

            List<String> tableFieldsNameList = FilesMappingEnum.valueOf(fileTypeName).getTableFieldNameList();

            for (String row : rowsArray) {
                String[] objArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(row, "\t");

                LinkedHashMap<String, String> objMap = new LinkedHashMap<>();

                for (int j = 0; j < objArray.length; j++) {
                    objMap.put(tableFieldsNameList.get(j), StringUtils.replace(objArray[j], "\r", ""));
                }

                objectResultList.add(objMap);
            }
        } else {
            LinkedHashMap<String, String> errorMap = new LinkedHashMap<>();

            if (StringUtils.isBlank(payload)) {
                errorMap.put("error", "Payload is empty or null");
            } else if (fileTypeName == null) {
                errorMap.put("error", "fileTypeName is empty or null");
            } else if (!isEnumContainsFileType) {
                errorMap.put("error", "fileTypeName not present in FilesMappingEnum");
            } else {
                errorMap.put("error", "unknown error");
            }

            objectResultList.add(errorMap);
        }

        return objectResultList;
    }

    public static List<String> getKeyList(String fileTypeName){

        try {
            return FilesMappingEnum.valueOf(fileTypeName).getTableFieldNameList();
        } catch (Exception ex){
            return null;
        }
    }

    private enum FilesMappingEnum {

//      dm+d files
        VMP(Arrays.asList("vpid","vpiddt","vpidprev","vtmid","invalid","nm","abbrevnm","basiscd","nmdt","nmprev","basisPrevcd","nmchangecd","combprodcd","presStatcd","sugF","gluF","presF","cfcF","nonAvailcd","nonAvaildt","dfIndcd","udfs","udfsUomcd","unitDoseUomcd")),
        VTM(Arrays.asList("vtmid","invalid","nm","abbrevnm","vtmidprev","vtmiddt")),
        DTINFO(Arrays.asList("vppid", "payCatcd", "price", "dt", "prevprice")),
        VPI(Arrays.asList("vpid","isid","basisStrntcd","bsSubid","strntNmrtrVal","strntNmrtrUomcd","strntDnmtrVal","strntDnmtrUomcd")),
        DFORM(Arrays.asList("vpid", "formcd")),
        DROUTE(Arrays.asList("vpid", "routecd")),
        CONTROL_INFO(Arrays.asList("vpid","catcd","catdt","catPrevcd")),
        VMPP(Arrays.asList("vppid","invalid","nm","vpid","qtyval","qtyUomcd","combpackcd")),
        CCONTENT_VMPP(Arrays.asList("prntvppid","chldvppid")),
        AMP(Arrays.asList("apid","invalid","vpid","nm","abbrevnm","desc","nmdt","nmPrev","suppcd","licAuthcd","licAuthPrevcd","licAuthchangecd","licAuthchangedt","combprodcd","flavourcd","ema","parallelImport","avalRestrictcd")),
        LIC_ROUTE(Arrays.asList("apid","routecd")),
        AP_INFO(Arrays.asList("apid","szWeight","colourcd","prodOrderNo")),
        AMPP(Arrays.asList("appid","invalid","nm","vppid","apid","combpackcd","legalCatcd","subp","disccd","discdt")),
        PACK_INFO(Arrays.asList("appid","reimbStatcd","reimbStatdt","reimbStatprevcd","packOrderNo")),
        PRESCRIB_INFO(Arrays.asList("appid","sched2","acbs","padm","fp10Mda","sched1","hosp","nurseF","enurseF","dentF")),
        PRICE_INFO(Arrays.asList("appid","price","pricedt","pricePrev","priceBasiscd")),
        REIMB_INFO(Arrays.asList("appid","pxChrgs","dispFees","bb","ltdStab","calPack","specContcd","dnd","fp34d")),
        CCONTENT_AMPP(Arrays.asList("prntappid","chldappid")),
        INGREDIENT(Arrays.asList("isid","isiddt","isidprev","invalid","nm")),
        COMBINATION_PACK_INDICATOR(Arrays.asList("cd","desc")),
        COMBINATION_PROD_INDICATOR(Arrays.asList("cd","desc")),
        NAMECHANGE_REASON(Arrays.asList("cd","desc")),
        VIRTUAL_PRODUCT_PRES_STATUS(Arrays.asList("cd","desc")),
        CONTROL_DRUG_CATEGORY(Arrays.asList("cd","desc")),
        LICENSING_AUTHORITY(Arrays.asList("cd","desc")),
        UNIT_OF_MEASURE(Arrays.asList("cd","cddt","cdprev","desc")),
        FORM(Arrays.asList("cd","cddt","cdprev","desc")),
        ROUTE(Arrays.asList("cd","cddt","cdprev","desc")),
        DT_PAYMENT_CATEGORY(Arrays.asList("cd","desc")),
        SUPPLIER(Arrays.asList("cd","cddt","cdprev","invalid","desc")),
        REIMBURSEMENT_STATUS(Arrays.asList("cd","desc")),
        SPEC_CONT(Arrays.asList("cd","desc")),
        VIRTUAL_PRODUCT_NON_AVAIL(Arrays.asList("cd","desc")),
        DISCONTINUED_IND(Arrays.asList("cd","desc")),
        PRICE_BASIS(Arrays.asList("cd","desc")),
        LEGAL_CATEGORY(Arrays.asList("cd","desc")),
        AVAILABILITY_RESTRICTION(Arrays.asList("cd","desc")),
        LICENSING_AUTHORITY_CHANGE_REASON(Arrays.asList("cd","desc")),
        VERSION(Arrays.asList("iWeekNumber","sDMDVersion")),

//      Multilex files
        MDDF_Product(Arrays.asList("iProductID","iChangeTypeID","dDateOfChange","iCoreGenericProductID","iLegacyLabelID","sDisplayName","sDisplayNameTM","sName","sNameTM","iQualifierID","iFormulationID","iStrengthID","sOrderNo","iLegacyLegalID","iCompanyID","iStatusID","bCHM","iPrescribeByID","bSugarFree","iProductTypeID","iProductClassID","iEWApplianceTariffHierID","iScotApplianceTariffHierID","bParallelImport","dProdAddDate","dProdDiscDate","iBaseFormulationID","iClinicalSetID","iPersonalAdminID","bHighRisk","iPickListOrderKey","iPickListOrderKey2","bIsEWDentist","bIsEWNurse","bIsEWMASPrescribable","bIsScoDentist","bIsScoNurse","bIsScoMASPrescribable","bIsNIDentist","bIsNINurse","bIsNIMASPrescribable","bCanOverrideDoseFreq","fMaxDoseBeforeReview","bIsUnlicensed","bIsSupported","iComplementaryTherapyTypeID","bCumulativeDosePrescribe","bCumulativeDoseAdmin","iLegalID","iControlledDrugScheduleID","bEWNHSPrescribable","bScotNHSPrescribable","bNINHSPrescribable","bWordsAndFiguresRequired")),
        MDDF_Pack(Arrays.asList("iProductID","iPackID","iChangeTypeID","dDateOfChange","iPackDescriptorID","fPackSize","sPreferredPackDescriptor","bPriceable","iEWPackPrice","iLegacyLegalID","iPackStatusID","bDivisible","iFlavourColourID","dPackAddDate","dPackDiscDate","sEAN","sPIPID","bEWNurse","bEWDental","iEWDrugTariffCategoryID","iScotPackPrice","bScotNurse","bScotDental","iScotDrugTariffCategoryID","iGP10ID","iSubPackSize","iCalendarPackID","bSpecialContainer","bNINurse","bNIDental","iGenericPackID","bIsEWMASDispensable","bIsScoMASDispensable","bIsNIMASDispensable","iPatientCharge","bContraceptiveUse","iNumOfProfessionalFees","iLegalID","iControlledDrugScheduleID","bEWNHSReimbursable","bScotNHSReimbursable","bNINHSReimbursable")),
        MDDF_linkProductPackDMD(Arrays.asList("iProductID","iPackID","iChangeTypeID","dDateOfChange","iDMDIdentifier","sDMDNM","sDMDDesc","sDMDAbbrevNM","iDMDTypeID")),
        MDDF_hierEWAppliance(Arrays.asList("iEWApplianceTariffHierID","iChangeTypeID","dDateOfChange","sEWApplianceHierarchyText","sEWApplianceHierarchy","iParent","iNextGroup","iFirstSubGroup")),
        MDDF_defScotDT(Arrays.asList("iScotDrugTariffCategoryID","iChangeTypeID","dDateOfChange","sScotDrugTariffCategoryText")),
        MDDF_dictRegion(Arrays.asList("iRegionID","iChangeTypeID","dDateOfChange","sRegionText")),
        MDDF_defGP10(Arrays.asList("iGP10ID","iChangeTypeID","dDateOfChange","sGP10Text")),
        MDDF_TictacAttributes(Arrays.asList("iAccessionID","iChangeTypeID","dDateOfChange","sRearImagePath","sSideImagePath","sFrontImagePath","sShape","sColour","sMarking")),
        MDDF_linkTicTac(Arrays.asList("iProductID","iAccessionID","iChangeTypeID","dDateOfChange")),
        MDDF_Version(Arrays.asList("dBuildDate","dMDDFDate","iStructureVersionID","iReleaseVersionID","iPreviousReleaseVersionID","sDataCutReference","sNHSSCTVersion")),
        MDDF_defMandatoryInstructionType(Arrays.asList("iMandatoryInstructionTypeID","iChangeTypeID","dDateOfChange","sMandatoryInstructionTypeText")),
        MDDF_dictMandatoryInstruction(Arrays.asList("iMandatoryInstructionID","iChangeTypeID","dDateOfChange","sMandatoryInstructionText","sInpatientText","iMandatoryInstructionTypeID","dBuildDate")),
        MDDF_linkMandatoryInstruction(Arrays.asList("iProductID","iChangeTypeID","dDateOfChange","iMandatoryInstructionID","iOrderKey")),
        MDDF_linkDMDDecisionSupport(Arrays.asList("iDMDIdentifier","iChangeTypeID","dDateOfChange","iProductID")),
        MDDF_defChangeType(Arrays.asList("iChangeTypeID","iThisRecordChangeTypeID","dDateOfChange","sRecordStatusText")),
        MDDF_hierScotAppliance(Arrays.asList("iScotApplianceTariffHierID","iChangeTypeID","dDateOfChange","sScotApplianceHierarchyText","sScotApplianceHierarchy","iParent","iNextGroup","iFirstSubGroup")),

//      McKesson files
        McK_defColdStorage(Arrays.asList("iColdStorageID","sColdStorageText")),
        McK_defNMSConditionCategory(Arrays.asList("iNMSConditionCategoryID","sNMSConditionCategoryText")),
        McK_defVATRate(Arrays.asList("iVATRateD","sVATRateText","fVATRateValue")),
        McK_SpecialHandling(Arrays.asList("iDMDIdentifier","bCDSafeStorage","iColdStorageID","bCytotoxicLine")),
        McK_ClozapineIndicator(Arrays.asList("iDMDIdentifier")),
        McK_EndorseMTM(Arrays.asList("iDMDIdentifier")),
        McK_EndorseMeasuredAndFitted(Arrays.asList("iDMDIdentifier")),
        McK_ApplicableVATRate(Arrays.asList("iDMDIdentifier","iVATRateID")),
        McK_NMSIndicator(Arrays.asList("iDMDIdentifier","iNMSConditionCatID")),
        McK_Reconstitution(Arrays.asList("iDMDIdentifier")),
        ;

        private List<String> tableFieldNameList;

        FilesMappingEnum(List<String> tableFieldNameList) {
            this.tableFieldNameList = tableFieldNameList;
        }

        public List<String> getTableFieldNameList() {
            return tableFieldNameList;
        }
        }

}



