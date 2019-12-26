package aoc.day14

import org.junit.Test
import kotlin.test.assertEquals


internal class Day14SpaceStoichiometryKtTest {

    @Test
    fun `input should return 31`() {
        val input =
                """
        10 ORE => 10 A
        1 ORE => 1 B
        7 A, 1 B => 1 C
        7 A, 1 C => 1 D
        7 A, 1 D => 1 E
        7 A, 1 E => 1 FUEL
                 """.trimIndent()

        val reactions = parseReactions(input)

        val ores = computeOREs("FUEL", 1, reactions)
        assertEquals(31, ores)
    }


    @Test
    fun `input should return 165`() {
        val input =
                """
                9 ORE => 2 A
                8 ORE => 3 B
                7 ORE => 5 C
                3 A, 4 B => 1 AB
                5 B, 7 C => 1 BC
                4 C, 1 A => 1 CA
                2 AB, 3 BC, 4 CA => 1 FUEL
                 """.trimIndent()

        val reactions = parseReactions(input)

        val ores = computeOREs("FUEL", 1, reactions)
        assertEquals(165, ores)
    }

    @Test
    fun `input should return 13312`() {
        val input =
                """
                157 ORE => 5 NZVS
                165 ORE => 6 DCFZ
                44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL
                12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ
                179 ORE => 7 PSHF
                177 ORE => 5 HKGWZ
                7 DCFZ, 7 PSHF => 2 XJWVT
                165 ORE => 2 GPVTF
                3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT
                 """.trimIndent()

        val reactions = parseReactions(input)

        val ores = computeOREs("FUEL", 1, reactions)
        assertEquals(13312, ores)
    }

    @Test
    fun `input should return 180697`() {
        val input =
                """
                2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG
                17 NVRVD, 3 JNWZP => 8 VPVL
                53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL
                22 VJHF, 37 MNCFX => 5 FWMGM
                139 ORE => 4 NVRVD
                144 ORE => 7 JNWZP
                5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC
                5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV
                145 ORE => 6 MNCFX
                1 NVRVD => 8 CXFTF
                1 VJHF, 6 MNCFX => 4 RFSQX
                176 ORE => 6 VJHF
                 """.trimIndent()

        val reactions = parseReactions(input)

        val ores = computeOREs("FUEL", 1, reactions)
        assertEquals(180697, ores)
    }

    @Test
    fun `input should return 2210736`() {
        val input =
                """
                171 ORE => 8 CNZTR
                7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL
                114 ORE => 4 BHXH
                14 VRPVC => 6 BMBT
                6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL
                6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT
                15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW
                13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW
                5 BMBT => 4 WPTQ
                189 ORE => 9 KTJDG
                1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP
                12 VRPVC, 27 CNZTR => 2 XDBXC
                15 KTJDG, 12 BHXH => 5 XCVML
                3 BHXH, 2 VRPVC => 7 MZWV
                121 ORE => 7 VRPVC
                7 XCVML => 6 RJRHP
                5 BHXH, 4 VRPVC => 5 LTCX
                 """.trimIndent()

        val reactions = parseReactions(input)

        val ores = computeOREs("FUEL", 1, reactions)
        assertEquals(2210736, ores)
    }


    @Test
    fun `input should return 362713`() {
        val input =
                """
                5 LKQCJ, 1 GDSDP, 2 HPXCL => 9 LVRSZ
                5 HPXCL, 5 PVJGF => 3 KZRTJ
                7 LVRSZ, 2 GFSZ => 5 FRWGJ
                9 ZPTXL, 5 HGXJH, 9 LQMT => 7 LVCXN
                2 LQMT, 2 PVJGF, 10 CKRVN => 9 VWJS
                2 VRMXL, 12 NBRCS, 2 WSXN => 7 GDSDP
                1 CKRP => 8 TBHVH
                1 SVMNB, 2 KZRTJ => 8 WKGQS
                6 LKQCJ, 8 HPXCL, 7 MPZH => 1 BQPG
                1 RCWL => 7 MPZH
                4 FGCMS, 2 LQMT, 1 LKQCJ => 1 KTBRM
                1 ZTCSK, 6 CXQB, 2 ZBZRT => 3 PVJGF
                7 DBNLM => 9 ZBZRT
                5 BGNQ, 2 WBPD, 5 KTBRM => 9 GFSZ
                6 XQBHG, 1 GPWVC => 8 CKFTS
                1 XWLQM, 29 XQBHG, 7 KPNWG => 5 BXVL
                6 TBHVH, 1 KTBRM => 7 HJGR
                1 LQMT, 14 KPNWG => 7 GPWVC
                18 LVCXN, 8 XVLT, 4 KPNWG, 13 LKQCJ, 12 MFJFW, 5 GZNJZ, 1 FLFT, 7 WBPD => 8 KZGD
                1 TBHVH => 1 VWKJ
                118 ORE => 2 CKRP
                2 LTCQX => 3 XQBHG
                1 GPWVC => 4 SMFQ
                6 CKRP => 4 RCWL
                39 LHZMD, 15 CKFTS, 26 HVBW, 57 KTBRM, 13 DFCM, 30 KZGD, 35 FPNB, 1 LKQCJ, 45 HJGR, 22 RCZS, 34 VWKJ => 1 FUEL
                1 BQPG, 2 BGNQ, 12 WBPD => 8 LTCQX
                2 WSXN => 2 HPXCL
                3 GRFPX => 5 XVLT
                1 LVRSZ => 3 SVMNB
                6 HLMT => 9 ZPTXL
                20 GFSZ => 5 GZNJZ
                1 RCWL => 9 KPNWG
                24 BGNQ, 31 KTBRM => 8 FLFT
                14 VSVG => 9 DBNLM
                191 ORE => 8 CXQB
                115 ORE => 2 SWVLZ
                17 KZRTJ, 13 KPNWG => 7 CKRVN
                9 BQPG => 4 XWLQM
                4 SMFQ, 2 GRFPX => 1 MFJFW
                6 CXQB, 4 CKRP, 2 BXVL, 5 GZNJZ, 3 VWJS, 1 FLFT, 4 KPNWG => 7 DFCM
                1 TBHVH => 6 BGNQ
                3 LQMT => 7 HLMT
                11 GDSDP => 4 WBPD
                2 KPNWG, 5 VWJS, 33 NBRCS => 7 NVDW
                5 GDSDP => 6 FGCMS
                1 GPWVC, 7 BGNQ, 1 FRWGJ => 8 GRFPX
                23 KTBRM, 11 VRMXL, 6 GPWVC => 5 SRJHK
                2 XQBHG, 1 GZNJZ => 3 HVBW
                1 ZTCSK => 4 WSXN
                1 XVLT, 5 HLMT, 1 ZPTXL, 2 HVBW, 7 NVDW, 1 WKGQS, 1 LTCQX, 5 MPZH => 3 FPNB
                16 SRJHK => 6 DWBW
                1 SVMNB, 1 VRMXL => 3 HGXJH
                133 ORE => 6 VSVG
                3 NBRCS, 1 FGCMS => 4 LQMT
                1 CKRP => 4 ZTCSK
                5 CKRVN, 1 FLFT => 1 RCZS
                4 ZTCSK, 15 RCWL => 9 LKQCJ
                1 SWVLZ => 8 NBRCS
                5 CKRP, 14 CXQB => 5 VRMXL
                1 SMFQ, 1 DWBW => 2 LHZMD
                 """.trimIndent()

        val reactions = parseReactions(input)

        val ores = computeOREs("FUEL", 1, reactions)
        assertEquals(362713, ores)
    }


    @Test
    fun `input could produce 82892753 FUEL for trillion OREs`() {
        val input =
                """
                157 ORE => 5 NZVS
                165 ORE => 6 DCFZ
                44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL
                12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ
                179 ORE => 7 PSHF
                177 ORE => 5 HKGWZ
                7 DCFZ, 7 PSHF => 2 XJWVT
                165 ORE => 2 GPVTF
                3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT
                 """.trimIndent()

        val reactions = parseReactions(input)

        val fuel = task2(reactions)
        assertEquals(82892753, fuel)
    }

    @Test
    fun `input could produce 5586022 FUEL for trillion OREs`() {
        val input =
                """
                2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG
                17 NVRVD, 3 JNWZP => 8 VPVL
                53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL
                22 VJHF, 37 MNCFX => 5 FWMGM
                139 ORE => 4 NVRVD
                144 ORE => 7 JNWZP
                5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC
                5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV
                145 ORE => 6 MNCFX
                1 NVRVD => 8 CXFTF
                1 VJHF, 6 MNCFX => 4 RFSQX
                176 ORE => 6 VJHF
                 """.trimIndent()

        val reactions = parseReactions(input)

        val fuel = task2(reactions)
        assertEquals(5586022, fuel)
    }

    @Test
    fun `input could produce 460664 FUEL for trillion OREs`() {
        val input =
                """
                171 ORE => 8 CNZTR
                7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL
                114 ORE => 4 BHXH
                14 VRPVC => 6 BMBT
                6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL
                6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT
                15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW
                13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW
                5 BMBT => 4 WPTQ
                189 ORE => 9 KTJDG
                1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP
                12 VRPVC, 27 CNZTR => 2 XDBXC
                15 KTJDG, 12 BHXH => 5 XCVML
                3 BHXH, 2 VRPVC => 7 MZWV
                121 ORE => 7 VRPVC
                7 XCVML => 6 RJRHP
                5 BHXH, 4 VRPVC => 5 LTCX
                 """.trimIndent()

        val reactions = parseReactions(input)

        val fuel = task2(reactions)
        assertEquals(460664, fuel)
    }

    @Test
    fun `input could produce 3281820 FUEL for 1 trillion OREs`() {
        val input =
                """
                5 LKQCJ, 1 GDSDP, 2 HPXCL => 9 LVRSZ
                5 HPXCL, 5 PVJGF => 3 KZRTJ
                7 LVRSZ, 2 GFSZ => 5 FRWGJ
                9 ZPTXL, 5 HGXJH, 9 LQMT => 7 LVCXN
                2 LQMT, 2 PVJGF, 10 CKRVN => 9 VWJS
                2 VRMXL, 12 NBRCS, 2 WSXN => 7 GDSDP
                1 CKRP => 8 TBHVH
                1 SVMNB, 2 KZRTJ => 8 WKGQS
                6 LKQCJ, 8 HPXCL, 7 MPZH => 1 BQPG
                1 RCWL => 7 MPZH
                4 FGCMS, 2 LQMT, 1 LKQCJ => 1 KTBRM
                1 ZTCSK, 6 CXQB, 2 ZBZRT => 3 PVJGF
                7 DBNLM => 9 ZBZRT
                5 BGNQ, 2 WBPD, 5 KTBRM => 9 GFSZ
                6 XQBHG, 1 GPWVC => 8 CKFTS
                1 XWLQM, 29 XQBHG, 7 KPNWG => 5 BXVL
                6 TBHVH, 1 KTBRM => 7 HJGR
                1 LQMT, 14 KPNWG => 7 GPWVC
                18 LVCXN, 8 XVLT, 4 KPNWG, 13 LKQCJ, 12 MFJFW, 5 GZNJZ, 1 FLFT, 7 WBPD => 8 KZGD
                1 TBHVH => 1 VWKJ
                118 ORE => 2 CKRP
                2 LTCQX => 3 XQBHG
                1 GPWVC => 4 SMFQ
                6 CKRP => 4 RCWL
                39 LHZMD, 15 CKFTS, 26 HVBW, 57 KTBRM, 13 DFCM, 30 KZGD, 35 FPNB, 1 LKQCJ, 45 HJGR, 22 RCZS, 34 VWKJ => 1 FUEL
                1 BQPG, 2 BGNQ, 12 WBPD => 8 LTCQX
                2 WSXN => 2 HPXCL
                3 GRFPX => 5 XVLT
                1 LVRSZ => 3 SVMNB
                6 HLMT => 9 ZPTXL
                20 GFSZ => 5 GZNJZ
                1 RCWL => 9 KPNWG
                24 BGNQ, 31 KTBRM => 8 FLFT
                14 VSVG => 9 DBNLM
                191 ORE => 8 CXQB
                115 ORE => 2 SWVLZ
                17 KZRTJ, 13 KPNWG => 7 CKRVN
                9 BQPG => 4 XWLQM
                4 SMFQ, 2 GRFPX => 1 MFJFW
                6 CXQB, 4 CKRP, 2 BXVL, 5 GZNJZ, 3 VWJS, 1 FLFT, 4 KPNWG => 7 DFCM
                1 TBHVH => 6 BGNQ
                3 LQMT => 7 HLMT
                11 GDSDP => 4 WBPD
                2 KPNWG, 5 VWJS, 33 NBRCS => 7 NVDW
                5 GDSDP => 6 FGCMS
                1 GPWVC, 7 BGNQ, 1 FRWGJ => 8 GRFPX
                23 KTBRM, 11 VRMXL, 6 GPWVC => 5 SRJHK
                2 XQBHG, 1 GZNJZ => 3 HVBW
                1 ZTCSK => 4 WSXN
                1 XVLT, 5 HLMT, 1 ZPTXL, 2 HVBW, 7 NVDW, 1 WKGQS, 1 LTCQX, 5 MPZH => 3 FPNB
                16 SRJHK => 6 DWBW
                1 SVMNB, 1 VRMXL => 3 HGXJH
                133 ORE => 6 VSVG
                3 NBRCS, 1 FGCMS => 4 LQMT
                1 CKRP => 4 ZTCSK
                5 CKRVN, 1 FLFT => 1 RCZS
                4 ZTCSK, 15 RCWL => 9 LKQCJ
                1 SWVLZ => 8 NBRCS
                5 CKRP, 14 CXQB => 5 VRMXL
                1 SMFQ, 1 DWBW => 2 LHZMD
                 """.trimIndent()

        val reactions = parseReactions(input)

        val fuel = task2(reactions)
        assertEquals(3281820, fuel)
    }

    private fun parseReactions(input: String): Map<String, Reaction> {
        return input.split("\n")
                .map { Reaction.Parser.parse(it) }
                .associateBy { it.element.name }
    }
}