package sru.groupsix.workOrder.Incident;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploaderService {

	public void uploadFile(MultipartFile file);
}