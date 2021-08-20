package com.example.serverconstruction.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class DataAndListResponse<T> extends BasicResponse{
    
    private T data;
    private List<T> dataList;

}