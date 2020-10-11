package com.company;

public class Volume {

    public enum Measure {
        m, // метры
        ml, // милилитры
        l,  //литры
        ba // барель
    }

    ;
    private double value;
    private Measure type;

    public Volume(double value, Measure type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {

        String typeAsString = "";
        switch (this.type) {
            case m:
                typeAsString = "м^3";
                break;
            case ml:
                typeAsString = "мл.";
                break;
            case l:
                typeAsString = "л.";
                break;
            case ba:
                typeAsString = "баррель";
                break;
        }

        return String.format("%s %s", this.value, typeAsString);
    }

    public Volume add(double number) {
        Volume newVolume = new Volume(this.value + number, this.type);
        return newVolume;
    }

    /**
     * операция вычитания числа
     */
    public Volume subtract(double number) {
        return new Volume(this.value - number, this.type);
    }

    /**
     * операция умножения на число
     */
    public Volume multiply(double number) {
        return new Volume(this.value * number, this.type);
    }

    /**
     * операция деления на число
     */
    public Volume divide(double number) {
        return new Volume(this.value / number, this.type);
    }

    public Volume to(Measure newType) {
        // по умолчанию новое значение совпадает со старым
        double newValue = this.value;

        // если текущий тип -- метр^3
        if (this.type == Measure.m) {
            // в зависимости от того во что преобразовываем
            switch (newType) {
                case m: // если метры
                    newValue = this.value;
                    break;
                case ml: // если милилитры
                    newValue = this.value * 1000000;
                    break;
                case l: // если литры
                    newValue = this.value / 1000;
                    break;
                case ba: // если баррель
                    newValue = this.value * 6.289811;
                    break;
            }
        } else if (newType == Measure.m) {
            // а это новый код, который добавляем
            // в зависимости от того во что преобразовываем
            switch (this.type) {
                case m: // если в метры, кстати это лишний пункт, он никогда не случится
                    newValue = this.value;
                    break;
                case ml:
                    newValue = this.value / 1000000;
                    break;
                case l:
                    newValue = this.value * 1000;
                    break;
                case ba:
                    newValue = this.value / 6.289811;
                    break;
            }
        } else {

            newValue = this.to(Measure.m).to(newType).value;

        }


        return new Volume(newValue, newType);
    }

    /**
     * операция сложения с другой длиной
     */
    public Volume add(Volume otherLength) {
        // преобразуем в тип длины с которой складываем
        Volume otherLengthConverted = otherLength.to(this.type);
        // а потом просто воспользуемся операцией сложением со значением (по сути просто числом)
        return this.add(otherLengthConverted.value);
    }

    /**
     * операция вычитания с другой длиной
     */
    public Volume subtract(Volume otherVolume) {
        // преобразуем в тип длины от которой отнимаем
        Volume otherVolumeConverted = otherVolume.to(this.type);
        // а потом просто воспользуемся операцией вычитания со значением (по сути просто числом)
        return this.subtract(otherVolumeConverted.value);
    }

    @Override
    public boolean equals(Object obj) {
        // так как equals позволяет сравниться с любым типом,
        // то принято добавлять проверку, является ли obj правильного типа

        if (!(obj instanceof Volume)) {
            // и если нет, возвращает false, то бишь объекты не равны
            return false;
        }


        Volume objVolume = (Volume) obj;

        // ну а тут конвертим в тип this и сравниваем значения
        return objVolume.to(this.type).value == this.value;
    }

}
