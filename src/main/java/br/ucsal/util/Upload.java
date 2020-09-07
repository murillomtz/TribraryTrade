package br.ucsal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;



public class Upload {
	public Upload() {
	}


	public String anexos(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (ServletFileUpload.isMultipartContent(request)) {

			int cont = 0;

			ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());

			List fileItemsList = null;

			try {
				fileItemsList = servletFileUpload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			String optionalFileName = "";
			FileItem fileItem = null;

			Iterator it = fileItemsList.iterator();

			do {

				cont++;

				FileItem fileItemTemp = (FileItem) it.next();

				if (fileItemTemp.isFormField()) {
					if (fileItemTemp.getFieldName().equals("file")) {
						optionalFileName = fileItemTemp.getString();
					}
				} else {
					fileItem = fileItemTemp;
				}

				if (cont != (fileItemsList.size())) {
					if (fileItem != null) {

						//String fileName = fileItem.getName();
						String fileName = alterarPath(fileItem);

						/*if (fileItem.getSize() > 0) {

	                            if (optionalFileName.trim().equals("")) {
	                                fileName = FilenameUtils.getName(fileName);
	                            } else {
	                                fileName = optionalFileName;
	                            }

	                            String dirName = "./webapp/img_livros/"; //caminho para o projeto

	                            File saveTo = new File(dirName + fileName);

	                            System.out.println("caminho: " + saveTo.toString() );
	                            try {
	                                fileItem.write(saveTo);
	                            } catch (Exception e) {
	                            }

	                        }*/
						return fileName;
					}
				}
			} while (it.hasNext());
			return null;
		} else {
			return null;
		}
	}
	private DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	public String alterarPath(FileItem  item) throws Exception{

		final String PATH_ARQUIVOS  ="./src/main/webapp/img/img_livros/";
		final String DIR_DATA_ATUAL = format.format(Calendar.getInstance().getTime());
		final String PATH_ABSOLUTO  = (PATH_ARQUIVOS + DIR_DATA_ATUAL );

		try {


			File diretorio  = new File(PATH_ABSOLUTO);

			// NESSE IF VC PA PERGUNTANDO SE EXISTE UM DIRETÓRIO, CASO NÃO IRÁ CRIAR
			// OU SEJA, SE FOR A PRIMEIRA IMAGEM DO DIA
			// ELE CRIARÁ O DIRETÓRIO <DIR_DATA_ATUAL> EX: 31-07-214
			if(!diretorio.exists())
				diretorio.mkdir();

			String fileName = item.getName();
			String arq[] = fileName.split("\\\\");
			for (int i = 0; i < arq.length; i++) {
				fileName = arq[i];
			}

			File file = new File(diretorio,fileName);

			FileOutputStream out = new FileOutputStream(file);
			//				InputStream in = ((ServletRequest) item).getInputStream();    

			// Imagens de até 2 megas !!
			//				byte[] buffer = new byte[1024 * 2];
			//				int nLidos;
			//				while ((nLidos = in.read(buffer)) >= 0) {
			//					out.write(buffer, 0, nLidos);
			//				}

			out.flush();
			out.close();


			// NO FINAL ELE TE RETORNA O CAMINHO DA PASTA ONDE VC SALVOU A IMAGEN
			// ESSA STRING VC PODE ARMAZENAR NA TABELA DO PRODUTO
			// NO CAMPO : CAMINHO_FOTO
			return "/" + DIR_DATA_ATUAL + "/" +item.getName();

		} catch (Exception e) {
			throw new Exception("Erro ao carregar imagem para o diretorio !!\n "
					+ "Error : "      + e.getMessage() 
					+ "\nCausa : " + e.getCause());
		}

	}

	public String inserirImagemDiretorio(FileItem item) throws IOException {

		String caminho = "./src/main/webapp/img_livros/";

		File diretorio = new File(caminho);
		if (!diretorio.exists()){
			diretorio.mkdir();
		}

		// Mandar o arquivo para o diretório informado
		String nome = item.getName();
		String arq[] = nome.split("\\\\");
		for (int i = 0; i < arq.length; i++) {
			nome = arq[i];
		}

		File file = new File(diretorio, nome);
		FileOutputStream output = new FileOutputStream(file);
		InputStream is = item.getInputStream();
		byte[] buffer = new byte[2048];
		int nLidos;
		while ((nLidos = is.read(buffer)) >= 0) {
			output.write(buffer, 0, nLidos);
		}
		output.flush();
		output.close();
		return caminho;
	}

}

