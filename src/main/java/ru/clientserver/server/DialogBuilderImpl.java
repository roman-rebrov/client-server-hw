package ru.clientserver.server;


import ru.clientserver.connections.Connect;

public class DialogBuilderImpl implements DialogBuilder {

    private boolean process = true;
    private String name = null;
    private String country = null;
    private boolean child = true;
    private DialogPoints step = DialogPoints.DEFAULT;

    @Override
    public boolean isProcess() {
        return this.process;
    }

    @Override
    public String append(String str) {

        if (str.equals(Connect.IS_DIALOG)){
            System.out.println(process? "Y" : "N");
            return process? "Y" : "N";
        }
        if (step == DialogPoints.DEFAULT) {
            step = DialogPoints.NAME;
            return "Write your name?";
        }
        if (str.length() == 0){
            return "The answer is not correct!";
        }
        if (step == DialogPoints.NAME) {
            this.name = str;
            step = DialogPoints.COUNTRY;
            return "Were are you from?";
        }
        if (step == DialogPoints.COUNTRY) {
            this.country = str;
            step = DialogPoints.AGE;
            return "Are you child? (yes/no)";
        }
        if (step == DialogPoints.AGE) {
            this.process = false;
            if (str.equals("yes")){
                return String.format("Welcome to the kids area, %s! Let's play!", this.name);
            }else if (str.equals("no")){
                return String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", this.name);
            }
        }


        return "The answer is not correct!";
    }
}
