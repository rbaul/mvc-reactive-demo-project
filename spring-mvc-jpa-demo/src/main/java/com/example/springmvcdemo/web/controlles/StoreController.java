package com.example.springmvcdemo.web.controlles;

import com.example.springmvcdemo.config.ApiImplicitPageable;
import com.example.springmvcdemo.services.StoreServiceImpl;
import com.example.springmvcdemo.web.dtos.StoreDto;
import com.example.springmvcdemo.web.dtos.ValidationGroups;
import com.example.springmvcdemo.web.dtos.errors.ErrorDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Store APIs")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/store")
public class StoreController {

    private final StoreServiceImpl storeService;

    @ApiOperation(value = "Get Store")
    @ApiResponses({@ApiResponse(code = 200, message = "Retrieved Store"),
            @ApiResponse(code = 404, message = "Store Not Found", response = ErrorDto.class)})
    @GetMapping("{storeId}")
    public StoreDto getStore(@PathVariable long storeId) {
        return storeService.get(storeId);
    }

    @ApiOperation(value = "Get All Store")
    @ApiResponses({@ApiResponse(code = 200, message = "Retrieved All Store")})
    @GetMapping
    public List<StoreDto> getAllStore() {
        return storeService.getAll();
    }

    @ApiOperation(value = "Create Store")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successfully Store created"),
            @ApiResponse(code = 428, message = "Invalid Store info", response = ErrorDto.class)})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public StoreDto create(
            @ApiParam(value = "Store object that needs to be create", name = "storeDto", required = true)
            @Validated(ValidationGroups.Create.class) @RequestBody StoreDto storeDto) {
        return storeService.create(storeDto);
    }

    @ApiOperation(value = "Update Store")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Store updated"),
            @ApiResponse(code = 428, message = "Invalid Store info", response = ErrorDto.class)})
    @PutMapping("{storeId}")
    public StoreDto update(@PathVariable long storeId,
                           @ApiParam(value = "Store object that needs to be edit", name = "storeDto", required = true)
                           @Validated(ValidationGroups.Create.class) @RequestBody StoreDto storeDto) {
        return storeService.update(storeId, storeDto);
    }

    @ApiOperation(value = "Delete Store")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successfully Store deleted"),
            @ApiResponse(code = 428, message = "Invalid Store Id", response = ErrorDto.class)})
    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("{storeId}")
    public void delete(@PathVariable long storeId) {
        storeService.delete(storeId);
    }

    @ApiOperation(value = "Fetch all Stores with paging")
    @ApiImplicitPageable
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully lists Stores")})
    @GetMapping("search")
    public Page<StoreDto> fetch(@PageableDefault @ApiIgnore(
            "Ignored because swagger ui shows the wrong params, " +
                    "instead they are explained in the implicit params"
    ) Pageable pageable) {
        return storeService.getAll(pageable);
    }

}
