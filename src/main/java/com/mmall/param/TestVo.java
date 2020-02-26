package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
public class TestVo {
    @NotBlank
    public String msg;
    @NotNull
    @Max(value=10,message="value")
    public Integer id;
    @NotEmpty
    public List<String> stringList;

}
