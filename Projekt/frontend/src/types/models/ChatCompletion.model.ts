import {CoverLetterModel} from "./CoverLetterModel.model";
import {UsageModel} from "./Usage.model";

export type ChatCompletionModel = {
    object: string;
    created: number;
    model: string;
    content: string;
    usage: UsageModel;
    coverLetter: CoverLetterModel;
}