package br.com.brewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.brewer.dto.FotosDTO;
import br.com.brewer.storage.FotoStorageRunnable;

@RestController
@RequestMapping(value="/fotos")
public class FotosController {

	@PostMapping
	public DeferredResult<FotosDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FotosDTO> resultado = new DeferredResult<>();
		System.out.println("size " + files.length);
		
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado));
		thread.start();
		
		return resultado;
	}
	
}
