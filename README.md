# MISAPolymorphicSSRsExtractorService

 This is a software pipeline, which allow the fast and reliable identification of polymorphic SSRs loci. The global processing is done by the concatenation of the programs MISA, BLAST and the PSSR-Extractor
script. The inputs are directory paths where multiple sequence files are found in FASTA format and the outputs are the SSRs, access codes to the databases, positions in the genome, number of repetitions and the degree of polymorphism expressed as range of variation, allelic
frequency, allele number and polymorphic information content (PIC). An optional script, SSRMerge, allows the identification of unique (non-redundant) loci in the set of processed
genome sequences with taxonomically closed relationship which permit the elaboration of the database taxadb that contains the clasification of several bacterias.

Citations:

MISA: This tool allows the identification and localization of perfect microsatellites. This program are executed by command line: the perl script is located at the folder web/resources/Misa/misa.pl

 - Beier S, Thiel T, Münch T, Scholz U, Mascher M (2017) MISA-web: a web server for microsatellite prediction. Bioinformatics 33 2583–2585. https://dx.doi.org/10.1093/bioinformatics/btx198

BLAST: Search in DB of Homologous sequences.

 - Altschul SF, Gish W, Miller W, Myers EW, Lipman DJ. Basic local alignment search tool. J Mol Biol. 1990 Oct 5;215(3):403-10. doi: 10.1016/S0022-2836(05)80360-2. PMID: 2231712.

 - The queries to blast are HTTP based interface. Searches are submitted to the NCBI servers. This is a public resource, so usage limitations apply. 
https://blast.ncbi.nlm.nih.gov/doc/blast-help/developerinfo.html#rest

 - For an optimal solution this project could use cloud blast but the shortage of resources did not allow it. https://ncbi.github.io/blast-cloud/dev/using-url-api.html 

PSSRExtractor: Identification of polymorphic SSR.

 - Martínez-Ortiz M, Rivero-Bandinez A. Metodología para el minado in silico de loci polimórficos en microsatélites. Revista Cubana de Informática Médica [Internet]. 2019 [citado 4 Jul 2024]; 11 (1) Disponible en: https://revinformatica.sld.cu/index.php/rcim/article/view/325

 # Quickstart

 1. Deploy the MISAPolymorphicSSRsExtractorService.war file (the original website was deployed on Glassfish 5.0 with jdk-1.8.0_144).
 2. Once in a server create the folder MISAPolymorphicSSRsExtractorService/web/resources/tempfiles.
 3. Import the taxadb.sql data base (the original database was imported to MariaDB 10.3.30).
 4. Launch the website.
