import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class GeneComparison {

    /**
     * Returns a set of genes that are present in mstB but not in mstA.
     * These are the "Added genes"
     * Added genes are those that were not present in the original MST in comparison to the second
     * (or comaprison mst)
     * 
     * @param mstA the first MST map (original)
     * @param mstB the second MST map (comparison)
     * @return a Set of gene names that were added in mstB
     */

    public static Set<String> getAddedGenes(Map<String, Map<String, String>> mstA, Map<String, Map<String, String>> mstB) {
        Set<String> genesA = new HashSet<>(mstA.keySet());
        Set<String> genesB = new HashSet<>(mstB.keySet());

        // Genes in B but not in A
        genesB.removeAll(genesA);
        return genesB;
    }

    /**
     * Returns a set of genes that are present in mstA but not in mstB.
     * These are the "Removed genes"
     * Removed genes are those that were present in the original MST but not in the comparison MST
     * 
     * @param mstA the first MST map (original)
     * @param mstB the second MST map (comparison)
     * @return a Set of gene names that are not present in MSTB (were romeved)
     */
    public static Set<String> getRemovedGenes(Map<String, Map<String, String>> mstA, Map<String, Map<String, String>> mstB) {
        Set<String> genesA = new HashSet<>(mstA.keySet());
        Set<String> genesB = new HashSet<>(mstB.keySet());

        // Genes in A but not in B
        genesA.removeAll(genesB);
        return genesA;
    }

    /* */
}
