package br.com.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.brewer.dto.FotosDTO;

public class FotoStorageRunnable implements Runnable {

	
	private MultipartFile[] files;
	private DeferredResult<FotosDTO> resultado;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotosDTO> resultado) {
		this.files = files;
		this.resultado = resultado;
	}

	@Override
	public void run() {
		String nomeFoto = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		//TODO: salvar a foto
		resultado.setResult(new FotosDTO(nomeFoto, contentType));

	}

}
