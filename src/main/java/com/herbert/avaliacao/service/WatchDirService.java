package com.herbert.avaliacao.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchDirService {	
	private static final String EXTENSAO_PERMITIDA = ".dat";
	private static final String DIRETORIO_ENTRADA  = System.getProperty("user.home")
													.concat(File.separator)
										            .concat("data")
										            .concat(File.separator)
										            .concat("in");	
	private static final String DIRETORIO_SAIDA    = System.getProperty("user.home")
													.concat(File.separator)
										            .concat("data")
										            .concat(File.separator)
										            .concat("out");
	private static final String EXTENSAO_SAIDA = ".done.dat";
	ArquivoService arquivoService = null;
		
	public void main() {
		arquivoService = new ArquivoService();
		
		Path path = Paths.get(DIRETORIO_ENTRADA);
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            path.register(watchService,
                          StandardWatchEventKinds.ENTRY_CREATE);
            startListening(watchService);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private void startListening(WatchService watchService) throws IOException, InterruptedException {
		WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {            	
                String filename = event.context().toString();
                if (EXTENSAO_PERMITIDA.equalsIgnoreCase(filename.substring(filename.length() - 4))) {
                    Path inputDir = Paths.get(DIRETORIO_ENTRADA).resolve((Path) event.context());                    
                    Path outputDir = Paths.get(DIRETORIO_SAIDA).resolve(filename.replace(EXTENSAO_PERMITIDA, EXTENSAO_SAIDA));
                    
                    System.out.println(inputDir.toString());
                    
                    List<String> lines = Files.readAllLines(inputDir);
                    arquivoService.readLines(lines);
                    this.produzirRelatotio(outputDir);
                }
            }
            key.reset();
        }
    }

	private void produzirRelatotio(Path outputDir) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(outputDir)) {
		    writer.write(arquivoService.getResultadoRelatorio());
		    System.out.println(outputDir.toString());
		    System.out.println(arquivoService.getResultadoRelatorio());
		} catch(IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			arquivoService.clear();
		}
	}
}