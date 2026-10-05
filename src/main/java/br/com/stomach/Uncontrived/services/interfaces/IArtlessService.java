package br.com.stomach.Uncontrived.services.interfaces;

import java.util.List;

import br.com.stomach.Uncontrived.models.Artless;

public interface IArtlessService {
//---
    public List<Artless> GetAll();
    public void Create(Artless doc);
}
