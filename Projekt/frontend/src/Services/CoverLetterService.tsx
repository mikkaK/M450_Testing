import api from '../config/Api';
import { CoverLetterModel } from '../types/models/CoverLetterModel.model';

const CoverLetterService = {
    addCoverLetter: (coverLetter: CoverLetterModel) => {
        return api.post('/cover-letter', coverLetter).then((res) => {
            return res.data;
        });
    },
};

export default CoverLetterService;
