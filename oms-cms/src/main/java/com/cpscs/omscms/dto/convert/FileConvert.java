package com.cpscs.omscms.dto.convert;


import java.util.List;

import com.cpscs.omscms.domain.FileDO;
import com.cpscs.omscms.dto.FileDTO;

//@org.mapstruct.Mapper
public interface FileConvert {
   // FileConvert MAPPER = Mappers.getMapper(FileConvert.class);

    public FileDTO do2dto(FileDO person);

    public List<FileDTO> dos2dtos(List<FileDO> list);
}