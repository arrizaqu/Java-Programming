# Spring Data JPA

## Search All Data Object
```
List<?> findAll();
```

## Search by Pagination
```
Page<?> findAll(Pageable pageable)
```

## Search By Name [=/equal]
```
Page<?> findByName(Pageable pageable);
```

## Search Like & Not Case Sensitive
```
Page<?> findByNameContainsIgnoreCase(String name, Pageable pageable);
```

## Multiple Parameter Search
```
Page<?> findAllByNameContainsIgnoreCaseAndProvince(String name, Province province, Pageable pageable);
```
