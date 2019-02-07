package br.com.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.brewer.storage.FotoStorage;


public class FotoStorageLocal implements FotoStorage {

	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getProperty("user.home"), "brewerfotos"));
	}
	
	public FotoStorageLocal(Path  path) {
		this.local = path;
		criarPastas();
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		if(files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			String novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				String nomeArquivo = this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome;
				arquivo.transferTo(new File(nomeArquivo));
				return novoNome;
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária.");
			}
		}
		return null;
	}
	
	private void criarPastas() {
		
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar fotos.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporária: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
	}
	
	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		if(logger.isDebugEnabled()) {
			logger.debug(String .format("Novo original %s, novo nome do arquivo %s", nomeOriginal, novoNome));
		}
		
		return novoNome;
	}
	
}