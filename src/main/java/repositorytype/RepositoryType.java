package repositorytype;

import java.util.Arrays;

public enum RepositoryType {
    BATTLE(0),BATTLE_MEMBER(1),COUNTRY(2),WARSHIP(3);
    private final int index;
    RepositoryType(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static RepositoryType findByIndex(int index){
       return Arrays.stream(RepositoryType.values())
               .filter(repositoryType -> repositoryType.getIndex() == index)
               .findFirst()
               .orElseThrow(()-> new IllegalArgumentException("Неправильный индекс"));


    }
}
