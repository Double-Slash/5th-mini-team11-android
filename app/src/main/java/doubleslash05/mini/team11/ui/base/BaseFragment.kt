package doubleslash05.mini.team11.ui.base

import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {
    protected val supportActionBar: ActionBar?
        get() = (activity as BaseActivity).supportActionBar

}
