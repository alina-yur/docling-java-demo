package org.demo;

import ai.docling.serve.api.convert.request.options.OutputFormat;
import io.quarkiverse.docling.runtime.client.DoclingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

@Path("/convert")
public class DoclingResource {

    @Inject
    DoclingService doclingService;

    @GET
    @Path("/url")
    @Produces(MediaType.TEXT_PLAIN)
    public String fromUrl(@RestQuery String url) {
        return doclingService.convertFromUri(URI.create(url), OutputFormat.MARKDOWN)
                .getDocument()
                .getMarkdownContent() + "\n";
    }

    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String fromFile(@RestForm("file") FileUpload file) throws IOException {
        return doclingService.convertFromBytes(
                Files.readAllBytes(file.uploadedFile()),
                file.fileName(),
                OutputFormat.MARKDOWN)
                .getDocument()
                .getMarkdownContent() + "\n";
    }
}
