import { Contato } from "src/app/contato/model/Contato";

export class Paginable {
    content: Contato[];
    totalElements: number;
    size: number;
    number: number;
}