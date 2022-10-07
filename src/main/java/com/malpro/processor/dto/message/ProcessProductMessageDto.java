package com.malpro.processor.dto.message;

import java.util.List;

import com.malpro.processor.dto.model.ModelProductDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 07.10.22.
 */
@Getter
@Setter
public class ProcessProductMessageDto {
    private List<ModelProductDto> productData;
}
